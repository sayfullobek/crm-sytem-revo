package it.revo.revoservice.controller;


import it.revo.revoservice.entity.enums.OperationEnum;
import it.revo.revoservice.repository.HistoryRepository;
import it.revo.revoservice.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/history")
@RequiredArgsConstructor
public class HistoryController {

    private final HistoryService historyService;
    private final HistoryRepository historyRepository;


    /**
     * Admin va Super Adminlar uchun history ma'lumotlarni olish
     *
     * @param page
     * @param size
     * @param tableName
     * @param objectId
     * @param operation
     * @return
     */

    @PreAuthorize("hasAuthority('GET_HISTORY')")
    @GetMapping
    public HttpEntity<?> getHistoryPageable(@RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "size", defaultValue = "10") int size,
                                            @RequestParam(value = "tableName", required = false) String tableName,
                                            @RequestParam(value = "objectId", required = false) String objectId,
                                            @RequestParam(value = "operation", required = false) String operation) {
        OperationEnum operationEnum = null;
        UUID objectUUID = null;
        if (page < 0 || size <= 0) {
            return ResponseEntity.badRequest().body("Bad request. Please check page and size");
        }
        if (objectId != null) {
            try {
                objectUUID = UUID.fromString(objectId);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("objectId is wrong");
            }
        }
        if (operation != null) {
            try {
                operationEnum = OperationEnum.valueOf(operation);
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body("operation is wrong");
            }
        }
//        if (tableName != null && tableName.toLowerCase().equals("orders")) {
//            return ResponseEntity.ok(historyService.getHistory(page, size, objectUUID, operationEnum));
//        }
        return ResponseEntity.ok(historyService.getHistory(page, size, tableName, objectUUID, operationEnum));
    }

    @PreAuthorize("hasAuthority('GET_HISTORY')")
    @GetMapping("/tableNames")
    public HttpEntity<?> getTableNames() {
        return ResponseEntity.ok(historyRepository.getAllTableNames());
    }
}
