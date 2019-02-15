package id.bkdana.agent.contarct;

public interface SubmitCollectionContract {

    void postSubmitCollection(String userId, String iDModAgent, String masterLoanId, String jmlTagihan, String sisaTagihan, String borrower_code);
}
