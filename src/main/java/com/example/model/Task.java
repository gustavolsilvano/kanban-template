package com.example.model;


public class Task {
    public enum TaskStatusEnum{
        PARA_FAZER(0),
        TRABALHANDO(1),
        ESPERANDO(2),
        FEITO(3);

        private final int value;

        private TaskStatusEnum(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }

    public int id;

    private TaskStatusEnum status;

    private String title;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public TaskStatusEnum getStatus(){
        return status;
    }

    public void setStatus(TaskStatusEnum status){
        this.status = status;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void moveForward(){
        int newValue = this.status.getValue() + 1;
        if (newValue > 3) {
            System.out.println("Não é possível mover para frente!");
            return;
        }
        this.status = TaskStatusEnum.valueOf(TaskStatusEnum.values()[newValue].name());
    }

    public void moveBackward() {
        int newValue = this.status.getValue() - 1;
        if (newValue < 0) {
            System.out.println("Não é possível mover para trás!");
            return;
        }
        this.status = TaskStatusEnum.valueOf(TaskStatusEnum.values()[newValue].name());
    }

    @Override
    public String toString(){
        String str = "Task id: %s, título: %s, status: %s";
        TaskStatusEnum statusTmp = getStatus();
        String statusStr = statusTmp.toString();
        if (statusTmp == TaskStatusEnum.PARA_FAZER) statusStr = "PARA FAZER";
        return String.format(str, getId(), getTitle(), statusStr);
    }
}
