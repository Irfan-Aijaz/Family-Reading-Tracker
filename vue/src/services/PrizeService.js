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

    updateClaimsStatus(claimIds, statusId) {
        return axios.post('/update_claim_requests', claimIds, {params: {claimStatusId: statusId}})
    }, 

    updateClaimStatus(claimId, statusId) {
        return axios.post('/update_claim_request', null, {params: {claimId: claimId, claimStatusId: statusId}})
    },

    getPrizeClaimCounters(familyId) {
        return axios.get('/get_prize_claim_counters/' + familyId)
    }



}