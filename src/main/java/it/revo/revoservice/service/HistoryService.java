package it.revo.revoservice.service;

import it.revo.revoservice.entity.History;
import it.revo.revoservice.entity.User;
import it.revo.revoservice.entity.enums.OperationEnum;
import it.revo.revoservice.payload.ResHistory;
import it.revo.revoservice.payload.ResPageable;
import it.revo.revoservice.repository.HistoryRepository;
import it.revo.revoservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;

    public ResPageable getHistory(int page, int size, String tableName, UUID objectId, OperationEnum operation) {

        historyRepository.updateHistoryObjectIsNull();
        if (objectId == null && tableName == null && operation == null) {
            Page<History> allByTableName = historyRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt"))));
            return makeResPageable(allByTableName);
        }
        if (tableName == null && objectId == null) {
            Page<History> allByTableName = historyRepository.findAllByOperationEnumIgnoreCase(operation, PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt"))));
            return makeResPageable(allByTableName);
        }
        if (tableName != null && operation != null && objectId == null) {
            Page<History> allByTableName = historyRepository.findAllByTableNameIgnoreCaseAndOperationEnumIgnoreCase(tableName, operation, PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt"))));
            return makeResPageable(allByTableName);
        }
        if (objectId != null && operation != null && tableName == null) {
            Page<History> allByTableName = historyRepository.findAllByObjectIdAndOperationEnumIgnoreCase(objectId, operation, PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt"))));
            return makeResPageable(allByTableName);
        }
        if (tableName != null && objectId == null) {
            Page<History> allByTableName = historyRepository.findAllByTableNameIgnoreCase(tableName, PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt"))));
            return makeResPageable(allByTableName);
        }
        if (tableName == null) {
            Page<History> allByTableName = historyRepository.findAllByObjectId(objectId, PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdAt"))));
            return makeResPageable(allByTableName);
        }
        return null;
    }

    private ResPageable makeResPageable(Page<History> allByTableName) {
        List<History> content = allByTableName.getContent();
        List<ResHistory> newContent = new ArrayList<>();
        for (History history : content) {
            User user = new User();
            user.setFirstName("User");
            user.setFirstName("Delete");
            newContent.add(
                    new ResHistory(
                            history.getTableName(),
                            history.getObjectId(),
                            history.getOperationEnum(),
                            history.getObject(),
                            history.getObjectId(),
                            history.getCreatedAt(),
                            history.getUpdateAt(),
                            history.getCreatedBy(),
                            history.getUpdateBy(),
                            userRepository.findById(history.getCreatedBy()).orElse(user)));
        }
        return new ResPageable(allByTableName.getNumber(), allByTableName.getSize(), allByTableName.getTotalPages(), allByTableName.getTotalElements(), newContent);
    }
//
//    private ResPageable makeResPageable2(Page<HistoryOrder> allForOrder) {
//        List<HistoryOrder> content = allForOrder.getContent();
//        List<ResHistory> newContent = new ArrayList<>();
//        for (HistoryOrder history : content) {
//            User user = new User();
//            user.setFirstName("User");
//            user.setFirstName("Delete");
//            newContent.add(
//                    new ResHistory(
//                            history.getObjectId(),
//                            history.getOperationEnum(),
//                            history.getObject(),
//                            history.getObjectId(),
//                            history.getCreatedAt(),
//                            history.getUpdatedAt(),
//                            history.getCreatedBy(),
//                            history.getUpdatedBy(),
//                            userRepository.findById(history.getCreatedBy()).orElse(user)));
//        }
//        return new ResPageable(allForOrder.getNumber(), allForOrder.getSize(), allForOrder.getTotalPages(), allForOrder.getTotalElements(), newContent);
//    }
//
//    public Object makeObject() {
//
//        return new Object();
//    }
}
