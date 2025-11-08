package org.fintech.paytech.domain.core.user.repository;

import org.fintech.paytech.domain.core.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /*@Query(value = "FROM Beneficiary b INNER JOIN b.wallet w WHERE w.walletId=?1 AND b.beneficiaryName =?2")
    public Beneficiary findByNameWallet(Integer walletId,String beneficiaryName);

    @Query(value = "FROM Beneficiary b INNER JOIN b.wallet w WHERE w.walletId=?1 AND b.beneficiaryMobileNumber =?2")
    public Beneficiary findByMobileWallet(Integer walletId,String beneficiaryMobileNumber);

    @Query(value = "FROM Beneficiary b INNER JOIN b.wallet w WHERE w.walletId=?1")
    public List<Beneficiary> findByWallet(Integer walletId);*/

    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);
}
