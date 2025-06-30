package admin;

import java.util.*;
import item.ItemVO;
import member.Member;

public class TransactionService {

    private final Map<Integer, Transaction> txMap = new HashMap<>();
    private int txSeq = 1;

    // 거래 생성
    public Transaction createTransaction(ItemVO item, Member buyer) {
        Transaction tx = new Transaction(txSeq++, item, buyer);
        txMap.put(tx.getId(), tx);
        return tx;
    }

    // 거래 ID로 조회
    public Transaction getTransaction(int id) {
        return txMap.get(id);
    }

    // 모든 거래 목록
    public List<Transaction> getAll() {
        return new ArrayList<>(txMap.values());
    }

    // 구매자로 필터링된 거래 목록
    public List<Transaction> getByBuyer(Member member) {
        return txMap.values().stream()
                .filter(tx -> tx.getBuyer() != null &&
                        tx.getBuyer().getNickname().equals(member.getNickname()))
                .toList();
    }

    // 거래 완료 처리 (상태 문자열로 설정)
    public boolean complete(int id) {
        Transaction tx = txMap.get(id);
        if (tx != null) {
            tx.getItem().setStatus("완료"); // ItemStatus.SOLD 대신 문자열
            // 거래를 보관하려면 삭제하지 말 것
            return true;
        }
        return false;
    }
}
