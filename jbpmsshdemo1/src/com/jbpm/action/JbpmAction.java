package com.jbpm.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;

import com.jbpm.dto.ProcessDto;
import com.jbpm.manager.JbpmManager;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class JbpmAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	// ���̶��弯��
	private List<ProcessDefinition> processDefinitions;
	// ����ʵ���
	private List<ProcessInstance> processInstances;
	// ���̲����
	private Map<String, Object> map;
	// ��ʷ����
	private List<HistoryProcessInstance> historyProcessInstances;
	// ��ǰ�û����񼯺�
	private List<Task> tasks;

	// ��ݴ�����ģ�Ͷ���
	private ProcessDto processDto;

	// ���ڽ��վ�����������ַ���ע�Ͳ���
	private String result;

	private JbpmManager jbpmManager;

	// ���ڽ���ǰ̨��������id����
	private String id;
	// ����ͼ����
	private ActivityCoordinates activityCoordinates;

	public String init() {
		String roleName = (String) ActionContext.getContext().getSession().get(
				"role");
		processDefinitions = jbpmManager.getProcessDefinitions();
		processInstances = jbpmManager.getProcessInstances();
		historyProcessInstances = jbpmManager.getHistoryProcessInstances();
		System.out.println("��ɫ���" + roleName);
		tasks = jbpmManager.getTasks(roleName);

		return SUCCESS;
	}

	/**
	 * ����������
	 * 
	 * @return
	 */
	public String deploy() {
		jbpmManager.deploy();
		init();
		return SUCCESS;
	}

	/**
	 * ɾ������Բ�������
	 * 
	 * @return
	 */
	public String undeploy() {
		jbpmManager.undeploy(id);
		return SUCCESS;
	}

	/**
	 * ����һ�����̲��������û�
	 * 
	 * @return
	 */
	public String start() {
		map = new HashMap<String, Object>();
		map.put("owner", (String) ActionContext.getContext().getSession().get(
				"role"));
		jbpmManager.start(id, map);
		return SUCCESS;
	}

	/**
	 * ��������
	 * 
	 * @return
	 */
	public String request() {
		return "request";
	}

	/**
	 * �����û���������
	 * 
	 * @return
	 */
	public String submit() {
		map = new HashMap<String, Object>();
		map.put("owner", processDto.getOwner());
		map.put("day", Integer.parseInt(processDto.getDay()));
		map.put("reason", processDto.getReason());
		map.put("name", processDto.getOwner());
		jbpmManager.complate(processDto.getTaskId(), map);
		init();
		return SUCCESS;
	}

	/**
	 * ������������
	 * 
	 * @return
	 */
	public String managerRequerst() {
		map = jbpmManager.manager(id);
		return SUCCESS;
	}

	/**
	 * ��������
	 * 
	 * @return
	 */
	public String submitManager() {
		jbpmManager.complateByManager(id, result);
		return SUCCESS;
	}

	/**
	 * �ϰ�����
	 * 
	 * @return
	 */
	public String bossRequest() {
		map = jbpmManager.boss(id);
		return SUCCESS;
	}

	/**
	 * �ϰ崦��
	 * 
	 * @return
	 */
	public String submitBoss() {
		jbpmManager.complateByBoss(id);
		return SUCCESS;
	}

	/**
	 * ������ʾ����ͼƬ
	 * 
	 * @return
	 */
	public String view() {
		activityCoordinates = jbpmManager.findActivityCoordinates(id);
		return SUCCESS;
	}

	/**
	 * ��ȡ����ͼƬ
	 * 
	 * @return
	 */
	public String pic() {
		InputStream inputStream = jbpmManager.findPicInputStream(id);
		PrintWriter pw = null;
		if (inputStream == null) {
			try {
				pw = ServletActionContext.getResponse().getWriter();
				pw.write("error");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				pw.close();
			}
		} else {
			byte[] b = new byte[1024];
			int len = -1;
			ServletOutputStream sos = null;
			try {
				sos = ServletActionContext.getResponse().getOutputStream();
				while ((len = inputStream.read(b, 0, 1024)) != -1) {
					sos.write(b, 0, len);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (sos != null) {
					try {
						sos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}
		return SUCCESS;
	}

	public List<ProcessDefinition> getProcessDefinitions() {
		return processDefinitions;
	}

	public void setProcessDefinitions(List<ProcessDefinition> processDefinitions) {
		this.processDefinitions = processDefinitions;
	}

	public List<ProcessInstance> getProcessInstances() {
		return processInstances;
	}

	public void setProcessInstances(List<ProcessInstance> processInstances) {
		this.processInstances = processInstances;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JbpmManager getJbpmManager() {
		return jbpmManager;
	}

	public void setJbpmManager(JbpmManager jbpmManager) {
		this.jbpmManager = jbpmManager;
	}

	public ProcessDto getProcessDto() {
		return processDto;
	}

	public void setProcessDto(ProcessDto processDto) {
		this.processDto = processDto;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<HistoryProcessInstance> getHistoryProcessInstances() {
		return historyProcessInstances;
	}

	public void setHistoryProcessInstances(
			List<HistoryProcessInstance> historyProcessInstances) {
		this.historyProcessInstances = historyProcessInstances;
	}

	public ActivityCoordinates getActivityCoordinates() {
		return activityCoordinates;
	}

	public void setActivityCoordinates(ActivityCoordinates activityCoordinates) {
		this.activityCoordinates = activityCoordinates;
	}
}
