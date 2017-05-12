/**
 * Created by wallance on 3/26/17.
 */
import { store } from '../store';

export function validateAuthority(nextState, replace, authority) {
    const { user } = store.getState();
    if (!user.loggedIn || !user.userInfo.authorityList) {
        replace({ pathname: '/' });
        return;
    }

    let match = false;

    user.userInfo.authorityList.forEach(authorityItem => {
        match = authorityItem.authority === authority;
    });

    if (!match) {
        replace({pathname: user.userInfo.roleList[0].url});
    }

}

export function getHomeUrl() {
    return store.getState().user.userInfo.authorityList[0].url;
}

// export history = {
//
//     get() {
//         return this.history;
//     },
//
//     set(history) {
//         this.history = history;
//     }
//
// };
