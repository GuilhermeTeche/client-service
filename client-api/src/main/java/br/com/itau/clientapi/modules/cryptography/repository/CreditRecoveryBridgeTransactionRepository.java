package br.com.itau.clientapi.modules.cryptography.repository;

import br.com.itau.clientapi.modules.cryptography.model.CreditRecoveryBridgeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRecoveryBridgeTransactionRepository extends JpaRepository<CreditRecoveryBridgeTransaction, String> {

    Optional<CreditRecoveryBridgeTransaction> findAccessTokenByRequestId(String requestId);
}
