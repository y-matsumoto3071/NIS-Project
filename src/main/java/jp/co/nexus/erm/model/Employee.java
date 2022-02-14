package jp.co.nexus.erm.model;

import java.util.Map;

/**
 * 社員管理機能で使用する社員情報を管理するクラス
 * Employee.java
 *
 * @author 松本 雄樹
 *
 */
public class Employee {
	/** 社員ID */
	private String employee_id;

	/** 社員名 */
	private String employee_name;

	/** 所属エリア */
	private String employee_area;

	/** 所属グループ */
	private String employee_group;

	/** 削除フラグ */
	private String deleteflg;

	/** 作成日付 */
	private String createdate;

	/** 更新日付 */
	private String updatedate;

	/** 社員情報を空文字で初期化 */
	public Employee() {
		this.employee_id = "";
		this.employee_name = "";
		this.employee_area = "";
		this.employee_group = "";
		this.deleteflg = "";
		this.createdate = "";
		this.updatedate = "";
	}

	/** 社員情報をmapで初期化
	 * @param map 社員情報テーブルから抽出したレコード
	 */
	public Employee(Map<String, Object> map) {
		this.employee_id = map.get("employee_id").toString();
		this.employee_name = map.get("employee_name").toString();
		this.employee_area = map.get("employee_area").toString();
		this.employee_group = map.get("employee_group").toString();
		this.deleteflg = map.get("deleteflg").toString();
		this.createdate = map.get("createdate").toString();
		this.updatedate = map.get("updatedate").toString();

	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_area() {
		return employee_area;
	}

	public void setEmployee_area(String employee_area) {
		this.employee_area = employee_area;
	}

	public String getEmployee_group() {
		return employee_group;
	}

	public void setEmployee_group(String employee_group) {
		this.employee_group = employee_group;
	}

	public String getDeleteflg() {
		return deleteflg;
	}

	public void setDeleteflg(String deleteflg) {
		this.deleteflg = deleteflg;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

}
