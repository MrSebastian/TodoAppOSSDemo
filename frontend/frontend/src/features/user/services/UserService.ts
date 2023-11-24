import UserInfoMapper from "@/features/user/services/api/model/UserInfoMapper";
import UserInfoClient from "@/features/user/services/api/UserInfoClient";
import { useUserStore } from "@/stores/user";

export default class UserService {
    private readonly userInfoClient = new UserInfoClient();

    private readonly userInfoMapper = new UserInfoMapper();

    private readonly userStore = useUserStore();
    loadUserInfo(): Promise<void> {
        return this.userInfoClient
            .getUserInfo()
            .then((response) => this.userInfoMapper.toUserInfo(response))
            .then((userInfo) => {
                this.userStore.setUsername(userInfo.username);
                this.userStore.setRoles(userInfo.roles);
            });
    }
}
