package com.pingplusplus.model;

import com.pingplusplus.exception.PingppException;
import com.pingplusplus.net.APIResource;
import com.pingplusplus.net.RequestOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order extends APIResource {
    String id;
    String object;
    Long created;
    Boolean livemode;
    String status;
    Boolean paid;
    Boolean refunded;
    Object app;
    String charge;
    String uid;
    String merchantOrderNo;
    Integer amount;
    Integer couponAmount;
    Integer actualAmount;
    Integer amountRefunded;
    Integer amountPaid;
    String currency;
    String subject;
    String body;
    String clientIp;
    Long timePaid;
    Long timeExpire;
    String coupon;
    ChargeCollection charges;
    String description;
    Map<String, Object> metadata;
    ChargeEssentials chargeEssentials;
    Long availableBalance;
    String receiptApp;
    String serviceApp;
    List<String> availableMethods;
    Integer discountAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Boolean getLivemode() {
        return livemode;
    }

    public void setLivemode(Boolean livemode) {
        this.livemode = livemode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public Boolean getRefunded() {
        return refunded;
    }

    public void setRefunded(Boolean refunded) {
        this.refunded = refunded;
    }

    public Object getApp() {
        return app;
    }

    public void setApp(Object app) {
        this.app = app;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCouponAmount() {
        return couponAmount;
    }

    public void setCouponAmount(Integer couponAmount) {
        this.couponAmount = couponAmount;
    }

    public Integer getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Integer actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Integer getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(Integer amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public Long getTimePaid() {
        return timePaid;
    }

    public void setTimePaid(Long timePaid) {
        this.timePaid = timePaid;
    }

    public Long getTimeExpire() {
        return timeExpire;
    }

    public void setTimeExpire(Long timeExpire) {
        this.timeExpire = timeExpire;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public ChargeCollection getCharges() {
        return charges;
    }

    public void setCharges(ChargeCollection charges) {
        this.charges = charges;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    public ChargeEssentials getChargeEssentials() {
        return chargeEssentials;
    }

    public void setChargeEssentials(ChargeEssentials chargeEssentials) {
        this.chargeEssentials = chargeEssentials;
    }

    public Long getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(Long availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getReceiptApp() {
        return receiptApp;
    }

    public void setReceiptApp(String receiptApp) {
        this.receiptApp = receiptApp;
    }

    public String getServiceApp() {
        return serviceApp;
    }

    public void setServiceApp(String serviceApp) {
        this.serviceApp = serviceApp;
    }

    public List<String> getAvailableMethods() {
        return availableMethods;
    }

    public void setAvailableMethods(List<String> availableMethods) {
        this.availableMethods = availableMethods;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public Integer getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Integer discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * ?????? order
     *
     * @param params
     * @return Order
     * @throws PingppException
     */
    public static Order create(Map<String, Object> params)
            throws PingppException {
        return create(params, null);
    }

    /**
     * ?????? order
     *
     * @param params
     * @param options the specific options
     * @return Order
     * @throws PingppException
     */
    public static Order create(Map<String, Object> params, RequestOptions options)
            throws PingppException {
        return APIResource.request(APIResource.RequestMethod.POST, classURL(Order.class), params, Order.class, options);
    }

    /**
     * ?????? order
     *
     * @param id
     * @return Order
     * @throws PingppException
     */
    public static Order retrieve(String id) throws PingppException {
        return retrieve(id, null, null);
    }

    /**
     * ?????? order
     *
     * @param id
     * @param params
     * @return Order
     * @throws PingppException
     */
    public static Order retrieve(String id, Map<String, Object> params) throws PingppException {
        return retrieve(id, params, null);
    }

    /**
     * ?????? order
     *
     * @param id
     * @param options the specific options
     * @return Order
     * @throws PingppException
     */
    public static Order retrieve(String id, RequestOptions options) throws PingppException {
        return retrieve(id, null, options);
    }

    /**
     * ?????? order
     *
     * @param id
     * @param params
     * @param options the specific options
     * @return Order
     * @throws PingppException
     */
    public static Order retrieve(String id, Map<String, Object> params, RequestOptions options) throws PingppException {
        return APIResource.request(APIResource.RequestMethod.GET, instanceURL(Order.class, id), params, Order.class, options);
    }

    /**
     * ?????? order ??????
     *
     * @param params
     * @return OrderCollection
     * @throws PingppException
     */
    public static OrderCollection list(Map<String, Object> params)
            throws PingppException {
        return list(params, null);
    }

    /**
     * ?????? order ??????
     *
     * @param params
     * @param options the specific options
     * @return OrderCollection
     * @throws PingppException
     */
    public static OrderCollection list(Map<String, Object> params, RequestOptions options)
            throws PingppException {
        return APIResource.request(APIResource.RequestMethod.GET, classURL(Order.class), params, OrderCollection.class, options);
    }

    /**
     * ?????? order
     *
     * @param id
     * @param params
     * @return Order
     * @throws PingppException
     */
    public static Order update(String id, Map<String, Object> params)
            throws PingppException {
        return update(id, params, null);
    }

    /**
     * ?????? order
     *
     * @param id
     * @param params
     * @param options the specific options
     * @return Order
     * @throws PingppException
     */
    public static Order update(String id, Map<String, Object> params, RequestOptions options)
            throws PingppException {
        return APIResource.request(APIResource.RequestMethod.PUT, instanceURL(Order.class, id), params, Order.class, options);
    }

    /**
     * ?????? order
     *
     * @return Order
     * @throws PingppException
     */
    public Order cancel()
            throws PingppException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", "canceled");
        return Order.update(this.getId(), params, null);
    }

    /**
     * ?????? order
     *
     * @param options the specific options
     * @return Order
     * @throws PingppException
     */
    public Order cancel(RequestOptions options)
            throws PingppException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", "canceled");
        return Order.update(this.getId(), params, options);
    }

    /**
     * ?????? order
     *
     * @param id
     * @return Order
     * @throws PingppException
     */
    public static Order cancel(String id)
            throws PingppException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", "canceled");
        return update(id, params);
    }

    /**
     * ?????? order
     *
     * @param id
     * @param options the specific options
     * @return Order
     * @throws PingppException
     */
    public static Order cancel(String id, RequestOptions options)
            throws PingppException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("status", "canceled");
        return update(id, params, options);
    }

    /**
     * ?????? order
     *
     * @param params
     * @return Order
     * @throws PingppException
     */
    public Order pay(Map<String, Object> params) throws PingppException {
        return Order.pay(this.getId(), params, null);
    }

    /**
     * ?????? order
     *
     * @param params
     * @param options the specific options
     * @return Order
     * @throws PingppException
     */
    public Order pay(Map<String, Object> params, RequestOptions options) throws PingppException {
        return Order.pay(this.getId(), params, options);
    }

    /**
     * ?????? order
     *
     * @param id
     * @param params
     * @return Order
     * @throws PingppException
     */
    public static Order pay(String id, Map<String, Object> params) throws PingppException {
        return pay(id, params, null);
    }

    /**
     * ?????? order
     *
     * @param id
     * @param params
     * @param options the specific options
     * @return Order
     * @throws PingppException
     */
    public static Order pay(String id, Map<String, Object> params, RequestOptions options) throws PingppException {
        return APIResource.request(APIResource.RequestMethod.POST, String.format("%s/pay",
                instanceURL(Order.class, id)), params, Order.class, options);
    }

    /**
     * ???????????? Charge ??????
     *
     * @param id
     * @param params
     * @return ChargeCollection
     * @throws PingppException
     */
    public static ChargeCollection chargeList(String id, Map<String, Object> params) throws PingppException {
        return chargeList(id, params, null);
    }

    /**
     * ???????????? Charge ??????
     *
     * @param id
     * @param params
     * @param options the specific options
     * @return ChargeCollection
     * @throws PingppException
     */
    public static ChargeCollection chargeList(String id, Map<String, Object> params, RequestOptions options) throws PingppException {
        return APIResource.request(APIResource.RequestMethod.GET, String.format("%s/charges", instanceURL(Order.class, id)),
                params, ChargeCollection.class, options);
    }

    /**
     * ???????????? Charge
     *
     * @param orderId
     * @param chargeId
     * @return Charge
     * @throws PingppException
     */
    public static Charge retrieveCharge(String orderId, String chargeId) throws PingppException {
        return retrieveCharge(orderId, chargeId, null);
    }

    /**
     * ???????????? Charge
     *
     * @param orderId
     * @param chargeId
     * @param options the specific options
     * @return Charge
     * @throws PingppException
     */
    public static Charge retrieveCharge(String orderId, String chargeId, RequestOptions options) throws PingppException {
        return APIResource.request(APIResource.RequestMethod.GET, String.format("%s/charges/%s", instanceURL(Order.class, orderId), chargeId),
                null, Charge.class, options);
    }
}
