package com.wallpad.ventilation.repository.common;

import com.wallpad.ventilation.repository.local.entities.VentilationStateEntity;

import java.util.ArrayList;
import java.util.List;

public class SerialParser {
    public static final String INTENT_ACTION_DEVICE_STATUS = "com.wallpad.intentAction.deviceStatus";
    public static final String INTENT_ACTION_DEVICE_STATUS_DATA_KEY = "com.wallpad.intentAction.deviceStatus.dataKey";

    public static final int CMD_DEVICE_INQUIRY = 0x4D;
    public static final int CMD_DEVICE_CONTROL = 0x4C;
    public static final int CMD_DEVICE_STATUS = 0x6C;

    public static final String VENTILATION_DEVICE_ID = "32";
    public static final String COMMAND_TYPE_REQUEST_STATE = "01";
    public static final String COMMAND_TYPE_RESPONSE_STATE = "81";
    public static final String COMMAND_TYPE_REQUEST_POWER_ON = "41";
    public static final String COMMAND_TYPE_REQUEST_VOLUME = "42";
    public static final String COMMAND_TYPE_REQUEST_MODE = "43";

    public static final int POS_DEVICE_CMD = 3;
    public static final int POS_DEVICE_ID = 5;
    public static final int POS_GROUP_SUB_ID = 6;
    public static final int POS_COMMAND_TYPE = 7;
    public static final int POS_DATA_LENGTH = 8;
    public static final int POS_DATA = 9;
    public static final int POS_DATA_ERROR = POS_DATA;
    public static final int POS_DATA_POWER = POS_DATA+1;
    public static final int POS_DATA_VOLUME = POS_DATA+2;
    public static final int POS_DATA_MODE = POS_DATA+3;
    public static final int POS_DATA_CONDITION = POS_DATA+4;

    public static final String DEVICE_FULL = "F";
    public static final int CHECK_SUM_LENGTH = 2;

    public static String getCmdStatus(int groupId) {
        return String.format("%s %s %s %s",
                VENTILATION_DEVICE_ID,
                ConvertNumber.decToHex(groupId, 1) + DEVICE_FULL,
                COMMAND_TYPE_REQUEST_STATE,
                ConvertNumber.decToHex(0, 2));
    }

    public static String getCmdControlPower(int groupId, int channelId, boolean on) {
        return String.format("%s %s %s %s %s",
                VENTILATION_DEVICE_ID,
                ConvertNumber.decToHex(groupId, 1) + ConvertNumber.decToHex(channelId, 1),
                COMMAND_TYPE_REQUEST_POWER_ON,
                ConvertNumber.decToHex(1, 2),
                ConvertNumber.decToHex(on?1:0, 2));
    }
    public static String getCmdControlVolume(int groupId, int channelId, int volume) {
        return String.format("%s %s %s %s %s",
                VENTILATION_DEVICE_ID,
                ConvertNumber.decToHex(groupId, 1) + ConvertNumber.decToHex(channelId, 1),
                COMMAND_TYPE_REQUEST_VOLUME,
                ConvertNumber.decToHex(1, 2),
                ConvertNumber.decToHex(volume, 2));
    }

    public static String getCmdControlMode(int groupId, int channelId, int mode) {
        return String.format("%s %s %s %s %s",
                VENTILATION_DEVICE_ID,
                ConvertNumber.decToHex(groupId, 1) + ConvertNumber.decToHex(channelId, 1),
                COMMAND_TYPE_REQUEST_MODE,
                ConvertNumber.decToHex(1, 2),
                ConvertNumber.decToHex(mode, 2));
    }

    public static List<VentilationStateEntity> parseState(String data) {
        String[] hexs = data.split(" ");
        int groupId = Integer.parseInt(hexs[POS_GROUP_SUB_ID].substring(0, 1));
        int channelId = Integer.parseInt(hexs[POS_GROUP_SUB_ID].substring(1, 2));
        int key = Mapper.getKey(groupId, channelId);
        int error = ConvertNumber.hexToDec(hexs[POS_DATA]);
        int power = ConvertNumber.hexToDec(hexs[POS_DATA_POWER]);
        int volume = ConvertNumber.hexToDec(hexs[POS_DATA_VOLUME]);
        int mode = ConvertNumber.hexToDec(hexs[POS_DATA_MODE]);
        int condition = ConvertNumber.hexToDec(hexs[POS_DATA_CONDITION]);
        List<VentilationStateEntity> states = new ArrayList<>();
        states.add(new VentilationStateEntity(key, groupId, channelId, error, power==1, volume, mode,
                isBitOn(condition, 5), isBitOn(condition, 4), isBitOn(condition, 3),
                isBitOn(condition, 2), isBitOn(condition, 1), isBitOn(condition, 0)));
        return states;
    }

    public static boolean isVentilationState(String data) {
        String[] hexs = data.split(" ");
        if ( hexs.length <= POS_COMMAND_TYPE ) return false;
        if ( ConvertNumber.hexToDec(hexs[POS_DEVICE_CMD]) != CMD_DEVICE_STATUS ) return false;
        if ( ConvertNumber.hexToDec(hexs[POS_DEVICE_ID]) != ConvertNumber.hexToDec(VENTILATION_DEVICE_ID) ) return false;
        return hexs[POS_COMMAND_TYPE].equals(COMMAND_TYPE_RESPONSE_STATE);
    }

    private static int ventilationCount(String[] hexs) {
        int dataLength = ConvertNumber.hexToDec(hexs[POS_DATA_LENGTH]);
        int length = hexs.length - (POS_DATA+CHECK_SUM_LENGTH);
        if ( dataLength != length ) return 0;
        return length/5;
    }

    private static boolean isBitOn(int value, int bitDigit) {
        int bitVal = ((Double) Math.pow(2, bitDigit)).intValue();
        return (value & bitVal) == bitVal;
    }
}
