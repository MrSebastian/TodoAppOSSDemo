import type UserInfoDTO from "@/features/user/services/api/model/UserInfoDTO";

export interface UserInfoClientInterface {
    getUserInfo(): Promise<UserInfoDTO>;
}
