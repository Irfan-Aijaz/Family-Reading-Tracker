import axios from 'axios';

export default {
    createPrize(prize) {
        return axios.post('/create_prize', prize)
    },

    updatePrize(prize) {
        return axios.put('/update_prize', prize)
    },

    getPrizesForFamilyUserGroup(familyId, userGroup) {
        return axios.get('/get_prizes_for_family_and_user_group', { params: {familyId: familyId, userGroup: userGroup}})
    },

    deleteInactivePrize(prizeId) {
        return axios.delete('/delete_inactive_prize/' + prizeId)
    },

    claimPrizeRequestChild(prizeId, childId) {
        return axios.put('/claim_prize_request_child', null, {params: {prizeId: prizeId, childId: childId}});
    },

    getClaimsForFamily(familyId) {
        return axios.get('/get_claims_for_family/' + familyId)
    },

    getClaimsForUser(userId) {
        return axios.get ('/get_claims_for_user/' + userId)
    },

    updateClaimsStatus(claimIds, statusId) {
        return axios.post('/update_claim_requests', claimIds, {params: {claimStatusId: statusId}})
    }, 

    updateClaimStatus(claimId, statusId) {
        return axios.post('/update_claim_request', null, {params: {claimId: claimId, claimStatusId: statusId}})
    },

    getPrizeClaimCounters(familyId) {
        return axios.get('/get_prize_claim_counters/' + familyId)
    },
    getPrizesForFamily(familyId) {
        return axios.get('/get_prizes_for_family/' + familyId)
    },
    claimPrizeForAdmin(prizeId, parentId) {
        return axios.put('/admin_claim', null, {params: {prizeId: prizeId, parentId: parentId}})
    },
    getPrizesForFamilyUserGroupClaiming(familyId, userGroup) {
        return axios.get('/get_prizes_for_family_and_user_group_claiming', { params: {familyId: familyId, userGroup: userGroup}})
    },

    getWonPrizesForUser(userId) {
        return axios.get('/get_won_prizes_for_user/'+userId)
    },

    getWonPrizesForFamily(familyId) {
        return axios.get('/get_won_prizes_for_family/'+ familyId)
    }



}