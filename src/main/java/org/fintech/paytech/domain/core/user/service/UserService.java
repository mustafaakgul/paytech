package org.fintech.paytech.domain.core.user.service;

import org.fintech.paytech.domain.core.user.dto.common.UserLoginDTO;
import org.fintech.paytech.domain.core.user.dto.common.UserLogoutDTO;
import org.fintech.paytech.domain.core.user.model.User;

public interface UserService {

    public Iterable<User> findAllUsers();

    public User createUser(User user);

    public String login(UserLoginDTO userLoginDTO);

    public boolean logout(UserLogoutDTO userLogoutDTO);

    public Beneficiary addBeneficiary(Beneficiary beneficiary, String key) throws BeneficiaryException, CustomerException, WalletException;

    public List<Beneficiary> findAllByWallet(Integer walletId) throws BeneficiaryException;

    public Beneficiary viewBeneficiary(String beneficiaryName, String key) throws BeneficiaryException, CustomerException;

    public List<Beneficiary> viewAllBeneficiary(String key) throws BeneficiaryException, CustomerException;

    public Beneficiary deleteBeneficiary(String key, BeneficiaryDTO beneficiaryDTO) throws BeneficiaryException, CustomerException;
}
