package com.digitalpetri.modbus.requests;

import java.nio.ByteBuffer;

import com.digitalpetri.modbus.FunctionCode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * This function is used to force each coil in a sequence of coils to either ON or OFF in a remote device. The Request
 * PDU specifies the coil references to be forced. Coils are addressed starting at zero. Therefore coil numbered 1 is
 * addressed as 0.
 * <p>
 * The requested ON/OFF states are specified by contents of the request data field. A logical '1' in a bit position of
 * the field requests the corresponding output to be ON. A logical '0' requests it to be OFF.
 */
public class WriteMultipleCoilsRequest extends ByteBufModbusRequest {

    private final int address;
    private final int quantity;

    /**
     * @param address  0x0000 to 0xFFFF (0 to 65535)
     * @param quantity 0x0001 to 0x07B0 (1 to 2000)
     * @param values   buffer of at least N bytes, where N = (quantity / 8) + (quantity % 8)
     */
    public WriteMultipleCoilsRequest(int address, int quantity, byte[] values) {
        this(address, quantity, Unpooled.wrappedBuffer(values));
    }

    /**
     * @param address  0x0000 to 0xFFFF (0 to 65535)
     * @param quantity 0x0001 to 0x07B0 (1 to 2000)
     * @param values   buffer of at least N bytes, where N = (quantity / 8) + (quantity % 8)
     */
    public WriteMultipleCoilsRequest(int address, int quantity, ByteBuffer values) {
        this(address, quantity, Unpooled.wrappedBuffer(values));
    }

    /**
     * Create a request using a {@link ByteBuf}. The buffer will have its reference count decremented after encoding.
     *
     * @param address  0x0000 to 0xFFFF (0 to 65535)
     * @param quantity 0x0001 to 0x07B0 (1 to 2000)
     * @param values   buffer of at least N bytes, where N = (quantity / 8) + (quantity % 8)
     */
    public WriteMultipleCoilsRequest(int address, int quantity, ByteBuf values) {
        super(values, FunctionCode.WriteMultipleCoils);

        this.address = address;
        this.quantity = quantity;
    }

    public int getAddress() {
        return address;
    }

    public int getQuantity() {
        return quantity;
    }

    public ByteBuf getValues() {
        return super.content();
    }

}
