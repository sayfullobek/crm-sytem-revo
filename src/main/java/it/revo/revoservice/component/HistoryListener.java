//package it.revo.revoservice.component;
//
//import it.revo.revoservice.entity.History;
//import it.revo.revoservice.entity.enums.OperationEnum;
//import it.revo.revoservice.entity.template.AbsNameEntity;
//import it.revo.revoservice.entity.template.AbsNameEntityTest;
//import it.revo.revoservice.repository.HistoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.PrePersist;
//import javax.persistence.PreRemove;
//import javax.persistence.PreUpdate;
//
//@Component
//public class HistoryListener {
//
//    // BU COMPONENT HAR BIR TABLELARDAGI O'ZGARISHLARNI ALOHIDA BOSHQA BIR TABLEGA YOZIB BORISH UCHUN YOZILDI!!!
//
//    @Autowired
//    HistoryRepository historyRepository;
//
//    public HistoryListener(@Lazy HistoryRepository historyRepository) {
//        this.historyRepository = historyRepository;
//    }
//
//    public HistoryListener() {
//
//    }
//
//    //BU METHOD TABLEGA INSERT BULGANDA QAYD QILIB BORADI
//    @PrePersist
//    public void insert(Object object) {
//        saveLogDuo(object, OperationEnum.INSERT);
//    }
//
//    //BU METHOD TABLEGA UPDATE BULGANDA QAYD QILIB BORADI
//    @PreUpdate
//    public void update(Object object) {
//        saveLogDuo(object, OperationEnum.UPDATE);
//    }
//
//    //BU METHOD TABLEGA DELETE BULGANDA QAYD QILIB BORADI
//    @PreRemove
//    public void remove(Object object) {
//        saveLogDuo(object, OperationEnum.DELETE);
//    }
//
//    public void saveLogDuo(Object object, OperationEnum operationEnum) {
//        String table = object.toString();
////        AbsEntity absEntity = (AbsEntity) object;
//        AbsNameEntityTest object1 = (AbsNameEntityTest) object;
//        History history = new History(
//                table.substring(0, table.indexOf("(")),
////                absEntity.getId(),
//                object1.getId(),
//                operationEnum,
//                object);
//        historyRepository.save(history);
//    }
//}
