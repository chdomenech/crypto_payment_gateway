package cryptogateway.vo.request;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionCheckVO {

    private Integer blockchainId;
    private String javaClass;
    private Collection<TransactionsVO> transactionsVO;
}
