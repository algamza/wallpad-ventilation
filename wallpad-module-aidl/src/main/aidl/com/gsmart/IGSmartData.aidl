// IGSmartData.aidl
package com.gsmart;

import com.gsmart.IGSmartDataCallback;
/**
* oneway is prevent that client's thread wait AIDL handle work
**/
interface IGSmartData {

    oneway void refreshNotificationInfo(int indexPage);

    oneway void refreshParkingInfo();

    oneway void refreshEnergyManagerInfo();

    oneway void refreshEnergyInfo();

    oneway void refreshDeliveryInfo(int indexPage);

    oneway void refreshVisitorInfo();

    oneway void refreshSettingsInfo();

    oneway void refeshWeatherInfo();

    oneway void refreshParkingParkingInquiry();

    oneway void refreshParkingLot(int indexPage);

    oneway void refreshLogin();


    oneway void refreshSubmenuEnergyElectricityInfo(boolean isRemoteApp,String mQueryMonth,String numberMonth,String Year);

    oneway void refreshSubmenuEnergyGasInfo(boolean isRemoteApp,String mQueryMonth,String numberMonth,String Year);

    oneway void refreshSubmenuEnergyWaterInfo(boolean isRemoteApp,String mQueryMonth,String numberMonth,String Year);

    oneway void refreshSubmenuEnergyHotWaterInfo(boolean isRemoteApp,String mQueryMonth,String numberMonth,String Year);

    oneway void refreshSubmenuEnergyHeatingInfo(boolean isRemoteApp,String mQueryMonth,String numberMonth,String Year);

    oneway void refreshSubmenuEnergyCoolingInfo(boolean isRemoteApp,String mQueryMonth,String numberMonth,String Year);


    oneway void refreshEnergyElectricityAmount(int amount);
    oneway void refreshEnergyGasAmount(int amount);
    oneway void refreshEnergyWaterAmount(int amount);
    oneway void refreshEnergyHotWaterAmount(int amount);
    oneway void refreshEnergyHeatingAmount(int amount);
    oneway void refreshEnergyCoolingAmount(int amount);

    oneway void refreshElectriccarCharging(int indexPage);
    oneway void refreshReferendum(int indexPage);

    oneway void requestElevatorStatus(int status);
    oneway void callElevator(String elevatorId, String direction);

    oneway void requestBatchBreakerStatus();
    oneway void requestBatchBreakerControl(boolean isOnOff);

    oneway void sendEnrollmentParking(String newParkingInfo);
    oneway void sendReferendumVote(String voteInfo, String voteKey);
    oneway void sendReferendumVoteCount(String voteKey);
    oneway void requestPasswordChange(String type, String password);

    void addClientListener(IGSmartDataCallback callback);

    void removeClientListener(IGSmartDataCallback callback);

    oneway void sendSerialData(int cmd, String dataHexString);
    oneway void handleDoorCameraPower(boolean isFromDoorToWallPad);
    oneway void handleDoorTurnOffCameraPower();
    oneway void sendTerminalAnswerFromCallApp();

    oneway void requestCallInformation(String type, String complex,String block,String unit);
    oneway void sendRequestChangeDeviceName(int deviceNo,String deviceType,int groupID,int subID,String deviceName);
    oneway void sendDeviceStatus(String detailBody);

    oneway void handleOpenDoorFromCallApp();
    oneway void handleChangeAudioDoor(int value);
    oneway void handleNotifyToSubPhoneFamilyCall();
    oneway void handleNotifyToSubPhoneFamilyEndCall();
    oneway void handleNotifyToWallPadFamilyCalling();
    oneway void handleNotifyToSubPhoneAnswerFamily();
    oneway void answerLobbyCallFromMainPhone();
    oneway void endCallLobbyCallFromMainPhone();
    oneway void notifyLobbyCalling();
    oneway void handleOpenDoorFromLobby();
    oneway void notifyGuardCalling();
    oneway void endCallGuardFromMainPhone();
    oneway void callGuardFromMainPhone();
    oneway void sendTalkOnForSmartPhone();
    oneway void notifyDeclineDoor();
    oneway void endCallAtMainPhone();
    oneway void sendImageToServer(String imageFileName,String base64,String location);
    oneway void handleCallFamilyFromWallPad();
    oneway void handlePlayMelody(String melodyType, String melodyPath, boolean melodyRepeat);
    oneway void handleStopMelody();
    oneway void handleChangeAudioCodecPath(int type);
    oneway void handleChangeLanguage(String language);
    oneway void sendCallPushRequestFormLobbyCall();

    oneway void NotifyBrightnessChange();
    oneway void setDataNTime(String dateTimeString);

    oneway void requestForSecuirtySensorStatus(String securityType, boolean isSecurityEnter);
    oneway void handleSetupRfCardRegister(boolean status);
    oneway void notifySecurityEventOperation();
    oneway void sendSecurityStatusChange();
    oneway void sendAlarmOperation(boolean status, String type);
}