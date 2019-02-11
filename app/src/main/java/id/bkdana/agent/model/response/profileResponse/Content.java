
package id.bkdana.agent.model.response.profileResponse;

import com.google.gson.annotations.SerializedName;


public class Content {

    @SerializedName("agent_created")
    private String mAgentCreated;
    @SerializedName("agent_email")
    private String mAgentEmail;
    @SerializedName("agent_fullname")
    private String mAgentFullname;
    @SerializedName("agent_password")
    private String mAgentPassword;
    @SerializedName("agent_phone")
    private String mAgentPhone;
    @SerializedName("agent_status")
    private String mAgentStatus;
    @SerializedName("agent_username")
    private String mAgentUsername;
    @SerializedName("id_mod_agent")
    private String mIdModAgent;

    public String getAgentCreated() {
        return mAgentCreated;
    }

    public void setAgentCreated(String agentCreated) {
        mAgentCreated = agentCreated;
    }

    public String getAgentEmail() {
        return mAgentEmail;
    }

    public void setAgentEmail(String agentEmail) {
        mAgentEmail = agentEmail;
    }

    public String getAgentFullname() {
        return mAgentFullname;
    }

    public void setAgentFullname(String agentFullname) {
        mAgentFullname = agentFullname;
    }

    public String getAgentPassword() {
        return mAgentPassword;
    }

    public void setAgentPassword(String agentPassword) {
        mAgentPassword = agentPassword;
    }

    public String getAgentPhone() {
        return mAgentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        mAgentPhone = agentPhone;
    }

    public String getAgentStatus() {
        return mAgentStatus;
    }

    public void setAgentStatus(String agentStatus) {
        mAgentStatus = agentStatus;
    }

    public String getAgentUsername() {
        return mAgentUsername;
    }

    public void setAgentUsername(String agentUsername) {
        mAgentUsername = agentUsername;
    }

    public String getIdModAgent() {
        return mIdModAgent;
    }

    public void setIdModAgent(String idModAgent) {
        mIdModAgent = idModAgent;
    }

}
