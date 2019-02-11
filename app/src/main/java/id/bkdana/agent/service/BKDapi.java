package id.bkdana.agent.service;

import id.bkdana.agent.model.response.dashboardResponse.DashboardResponse;
import id.bkdana.agent.model.response.detailSurveyResponse.DetailSurveyResponse;
import id.bkdana.agent.model.response.formSurverResponse.FormSurveyResponse;
import id.bkdana.agent.model.response.listMyCollectionResponse.ListCollectionResponse;
import id.bkdana.agent.model.response.listMySurveyResponse.ListMySurveyResponse;
import id.bkdana.agent.model.response.loginResponse;
import id.bkdana.agent.model.response.listSurveyResponse.ListSurveyResponse;
import id.bkdana.agent.model.response.profileResponse.ProfileResponse;
import id.bkdana.agent.model.response.scanBarcodeResponse.ScanBarcodeResponse;
import id.bkdana.agent.model.response.submitCollectionResponse.SubmitCollectionReponse;
import id.bkdana.agent.view.activity.DetailSurveyActivity;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface BKDapi {

    @FormUrlEncoded
    @POST("auth/login")
    Call<loginResponse> postLogin (@Field("username") String username,
                                  @Field("password") String password);

    @POST("agent/profile")
    Call<ProfileResponse> postProfile (@Header("Authorization") String Authorization);


    @GET("dashboard")
    Call<DashboardResponse> getDashboard (@Header("Authorization") String Authorization);

    @GET("survey/listsurvey")
    Call<ListSurveyResponse> getListSurvey (@Header("Authorization") String Authorization,
                                            @Query("page") String page,
                                            @Query("limit") String limit);

    @GET("survey/list_mysurvey")
    Call<ListMySurveyResponse> getListMySurvey (@Header("Authorization") String Authorization,
                                                @Query("id_agent") String id_agent,
                                                @Query("page") String page,
                                                @Query("limit") String limit);

    @GET("collection/list_mycollection")
    Call<ListCollectionResponse> getListMyCollection (@Header("Authorization") String Authorization,
                                                      @Query("id_agent") String id_agent,
                                                      @Query("page") String page,
                                                      @Query("limit") String limit);
    @FormUrlEncoded
    @POST("survey/submit_formsurvey1")
    Call<FormSurveyResponse> postFormSurvey1 (@Header("Authorization") String Authorization,
                                              @Field("id_agent") String id_agent,
                                              @Field("id_peminjam") String id_peminjam,
                                              @Field("master_loan_id") String master_loan_id,
                                              @Field("product_title") String product_title,
                                              @Field("nama") String nama,
                                              @Field("alamat") String alamat,
                                              @Field("no_ktp") String no_ktp);
    @Multipart
    @POST("survey/submit_formsurvey2")
    Call<FormSurveyResponse> postFormSurvey2 (@Header("Authorization") String Authorization,
                                              @Part ("id_mod_agent_survey") RequestBody id_mod_agent_survey,
                                              @Part("alamat_usaha")RequestBody alamat_usaha,
                                              @Part("jenis_usaha") RequestBody jenis_usaha,
                                              @Part MultipartBody.Part info_usaha_file);
    @FormUrlEncoded
    @POST("survey/submit_formsurvey3")
    Call<FormSurveyResponse> postFormSurvey3 (@Header("Authorization") String Authorization,
                                              @Field("id_mod_agent_survey") String id_mod_agent_survey,
                                              @Field("omset") String omset,
                                              @Field("biaya") String biaya,
                                              @Field("laba") String laba);

    @POST("collection/data_borrower")
    Call<ScanBarcodeResponse> postScanBarcode (@Header("Authorization") String Authorization,
                                               @Field("id_peminjam") String id_peminjam);

    @POST("collection/submit_collection")
    Call<SubmitCollectionReponse> postSubmitCollection (@Header("Authorization") String Authorization,
                                                        @Field("id_agent") String id_agent,
                                                        @Field("id_peminjam") String id_peminjam,
                                                        @Field("master_loan_id") String master_loan_id,
                                                        @Field("product_title") String product_title,
                                                        @Field("nama") String nama,
                                                        @Field("no_ktp") String no_ktp,
                                                        @Field("borrower_code") String borrower_code,
                                                        @Field("hutang_pokok") String hutang_pokok,
                                                        @Field("jumlah_pembayaran") String jumlah_pembayaran);
    @FormUrlEncoded
    @POST("survey/details_survey")
    Call<DetailSurveyResponse> postDetailMySurvey (@Header("Authorization") String Authorization,
                                                   @Field("id_mod_agent_survey") String id_mod_agent_survey);
}

