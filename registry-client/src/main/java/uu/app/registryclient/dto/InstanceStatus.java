package uu.app.registryclient.dto;

public enum InstanceStatus {
    UP,
    DOWN;

    public boolean isDown() {
        return this.equals(DOWN);
    }
}
