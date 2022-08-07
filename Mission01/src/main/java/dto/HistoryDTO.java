package dto;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class HistoryDTO {
	
	private int id;
	private BigDecimal lat;
	private BigDecimal lnt;
	private Timestamp query_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public BigDecimal getLnt() {
		return lnt;
	}
	public void setLnt(BigDecimal lnt) {
		this.lnt = lnt;
	}
	public Timestamp getQuery_date() {
		return query_date;
	}
	public void setQuery_date(Timestamp query_date) {
		this.query_date = query_date;
	}
	
}
