import type UserInfoClientFactory from "@/features/user/services/api/UserInfoClientFactory";
import type { UserInfoClientInterface } from "@/features/user/services/api/UserInfoClientInterface";

import UserInfoDTO from "@/features/user/services/api/model/UserInfoDTO";

export default class UserInfoLocalStorageClient
    implements UserInfoClientInterface
{
    private readonly KEY_USERINFO_NAME = "userinfo.name";
    private readonly KEY_USERINFO_ROLES = "userinfo.roles";

    private STORED_USERINFO_ROLES_SEPARATOR = ";";

    private readonly defaultUserName = "local user";
    private readonly defaultUserRoles: string[] = ["TASK_ADMIN", "TASK_READER"];

    getUserInfo(): Promise<UserInfoDTO> {
        return Promise.resolve(this.createUserInfoFromStoreOrWithDefaults());
    }

    private createUserInfoFromStoreOrWithDefaults(): UserInfoClientFactory {
        return new UserInfoDTO(
            this.getStoredUserName() ?? this.defaultUserName,
            this.getStoredRoles() ?? this.defaultUserRoles
        );
    }

    private getStoredUserName(): string | null {
        return localStorage.getItem(this.KEY_USERINFO_NAME);
    }

    private getStoredRoles(): string[] | null {
        const storedRolesConcated = localStorage.getItem(
            this.KEY_USERINFO_ROLES
        );
        return storedRolesConcated === null
            ? null
            : storedRolesConcated.split(this.STORED_USERINFO_ROLES_SEPARATOR);
    }
}
