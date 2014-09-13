package com.digitalpetri.modbus.requests;

import com.digitalpetri.modbus.FunctionCode;

public abstract class SimpleModbusRequest implements ModbusRequest {

    private final FunctionCode functionCode;

    protected SimpleModbusRequest(FunctionCode functionCode) {
        this.functionCode = functionCode;
    }

    @Override
    public FunctionCode getFunctionCode() {
        return functionCode;
    }
    
}
