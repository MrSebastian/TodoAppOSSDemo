import FetchUtils from "@/api/FetchUtils";
import UserInfoDTO from "@/features/user/services/api/model/UserInfoDTO";
import { API_BASE } from "@/Constants";

export default class UserInfoClient {
    private readonly URL = `${API_BASE}/api/sso/userinfo`;
    getUserInfo(): Promise<UserInfoDTO> {
        return fetch(this.URL, FetchUtils.getGETConfig())
            .then((response) => response.json())
            .catch((err) => {
                FetchUtils.defaultResponseHandler(
                    err,
                    "Berechtigungen konnten nicht erfasst werden"
                );
            });
    }
}
