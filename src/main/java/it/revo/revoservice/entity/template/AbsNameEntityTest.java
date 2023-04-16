//package it.revo.revoservice.entity.template;
//
//import it.revo.revoservice.component.HistoryListener;
//import lombok.Data;
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.Type;
//
//import javax.persistence.*;
//import java.util.UUID;
//
//@Data
//@MappedSuperclass
//@EntityListeners(HistoryListener.class)
//public abstract class AbsNameEntityTest {
//    @Id
//    @Type(type = "org.hibernate.type.PostgresUUIDType")
//    @GeneratedValue(generator = "uuid2")
//    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
//    private UUID id;
//
//    @Column(nullable = false)
//    private String name;
//}
