import UserInfoDTO from "@/features/user/services/api/model/UserInfoDTO";
import UserInfo from "@/features/user/types/UserInfo";

export default class UserInfoMapper {
    toUserInfo(userInfoDTO: UserInfoDTO): UserInfo {
        const userName = userInfoDTO.user_name ?? "";
        const userRoles = userInfoDTO.user_roles ?? [];

        return new UserInfo(userName, userRoles);
    }
}
