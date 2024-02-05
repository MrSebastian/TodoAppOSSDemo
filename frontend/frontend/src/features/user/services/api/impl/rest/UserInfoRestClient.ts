import type UserInfoDTO from "@/features/user/services/api/model/UserInfoDTO";
import type { UserInfoClientInterface } from "@/features/user/services/api/UserInfoClientInterface";

import FetchUtils from "@/api/FetchUtils";
import { API_BASE } from "@/Constants";

export default class UserInfoRestClient implements UserInfoClientInterface {
    private readonly URL = `${API_BASE}/api/sso/userinfo`;
    getUserInfo(): Promise<UserInfoDTO> {
        return fetch(this.URL, FetchUtils.getGETConfig()).then((response) => {
            FetchUtils.defaultResponseHandler(
                response,
                "Berechtigungen konnten nicht erfasst werden"
            );
            return response.json();
        });
    }
}
