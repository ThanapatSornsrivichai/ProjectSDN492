//getBuffer() to retrieve the circular buffer.
package net.floodlightcontroller.pktinhistory;

import net.floodlightcontroller.core.module.IFloodlightService;
import net.floodlightcontroller.core.types.SwitchMessagePair;
import net.floodlightcontroller.util.ConcurrentCircularBuffer;

public interface IPktinHistoryService extends IFloodlightService {
	public ConcurrentCircularBuffer<SwitchMessagePair> getBuffer();
}
