package it.revo.revoservice.repository;

import it.revo.revoservice.entity.History;
import it.revo.revoservice.entity.enums.OperationEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.UUID;

@CrossOrigin
public interface HistoryRepository extends JpaRepository<History, UUID> {

    Page<History> findAllByTableNameIgnoreCase(String tableName, Pageable pageable);

    Page<History> findAllByObjectId(UUID objectId, Pageable pageable);

    Page<History> findAllByTableNameIgnoreCaseAndOperationEnumIgnoreCase(String tableName, OperationEnum operation, Pageable pageable);

    Page<History> findAllByObjectIdAndOperationEnumIgnoreCase(UUID objectId, OperationEnum operation, Pageable pageable);

    Page<History> findAllByOperationEnumIgnoreCase(OperationEnum operation, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update history as ld set object_id=cll.objectId from (select id, cast(((cast(object as json)) ->>'id') as uuid) as objectId from history where object_id is null)as cll (id,objectId)where cll.id=ld.id", nativeQuery = true)
    public void updateHistoryObjectIsNull();

    @Query(nativeQuery = true,
            value = "select * from history where object_id=:objectId order by created_at desc  limit 1")
    History findForAdminNotification(@Param("objectId") UUID objectId);

    @Query(nativeQuery = true, value = "SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE' and table_name!='history'")
    List<String> getAllTableNames();
}
