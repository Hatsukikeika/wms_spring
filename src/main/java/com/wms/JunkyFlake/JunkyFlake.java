package com.wms.JunkyFlake;
import java.util.concurrent.TimeUnit;

public class JunkyFlake {
	
	/*
	 * Space distribution.
	 *
	 * TOTAL_BIT : 64 - 1 = 63, which 1 bit is not used
	 * ID_BIT : ID space = 2^16 - 1 = 65535 per second.
	 * NODE_BIT : Worker space =  2^6 - 1 = 63 worker limits.
	 * REPO_BIT : Table space = 2^12 - 1 = 4095 tables.
	 * Remaining bits for time = 29. Which equals 17 years.
	 */
	private final static long TOTAL_BIT = 63L;
	private final static long ID_BIT = 16L;
	private final static long NODE_BIT = 6L;
	private final static long REPO_BIT = 12L;

	/*
	 * Maximum value
	 */
	private final static long MAX_ID = (1L << ID_BIT) - 1;
	private final static long MAX_NODE = (1L << NODE_BIT) - 1;
	private final static long MAX_REPO = (1L << REPO_BIT) - 1;
	private final static long MAX_PRECISE = (1L << (TOTAL_BIT - ID_BIT - NODE_BIT - REPO_BIT)) - 1;
	
	/*
	 * Bit shift
	 */
	private final static long S_TIME = ID_BIT + NODE_BIT + REPO_BIT;
	private final static long S_ID = NODE_BIT + REPO_BIT;
	private final static long S_NODE = REPO_BIT;
	
	/*
	 * Default
	 * 
	 * long(1609477200) : 2021/1/1 00:00 EST
	 */
	private final long SINCE_EPOCH = 1609477200L;
	private final long NODE;
	private final long REPO;
	
	/*
	 * Track
	 */
	private long count;
	private long lastGen;
	
	
	public JunkyFlake() {
		this.NODE = 0L;
		this.REPO = 0L;
	}
	
	public JunkyFlake(long node, long repo) {
		if(node > MAX_NODE || node < 0L)
			throw new IllegalArgumentException(String.format("Node number out of range 0 ~ %d", MAX_NODE));
		
		if(repo > MAX_REPO || repo < 0L)
			throw new IllegalArgumentException(String.format("Repo number out of range 0 ~ %d", MAX_REPO));

		this.NODE = node;
		this.REPO = repo;
	}
	
	public synchronized long nextId() {
		
		long now = this.getTimeStamp();
		
		long deltaTime = now - SINCE_EPOCH;
		
		if(deltaTime > MAX_PRECISE)
			throw new JunkyException("out of usage");
		
		if(now < lastGen)
			throw new JunkyException(String.format("clock back for %d seconds", lastGen - now));
		
		if(now == lastGen) {
			count = (count + 1) & MAX_ID;
			if(count == 0) {
				while(now <= lastGen) {
					now = this.getTimeStamp();
				}
			}
		}else {
			count = 0L;
		}
		
		lastGen = now;
		
		return (deltaTime << S_TIME) |
				(count << S_ID)|
				(NODE << S_NODE)|
				REPO;
	}
	
	public long[] parse(long id) {
		
		long createTime = (id >> S_TIME) + SINCE_EPOCH; 
		long seq = (id & (((1L << ID_BIT) - 1) << S_ID)) >> S_ID;
		long worker = (id & (((1L << NODE_BIT) - 1) << S_NODE)) >> S_NODE;
		long repo = id & ((1L << REPO_BIT) - 1);
		
		return new long[] {createTime, worker, repo, seq};
	}
	
	
	/*
	 * this returns time in second unit.
	 */
	protected long getTimeStamp() {
		return TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
	}
	
}
