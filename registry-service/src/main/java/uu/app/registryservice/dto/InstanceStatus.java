package uu.app.registryservice.dto;

public enum InstanceStatus {
    UP,
    DOWN;

    public boolean isDown() {
        return this.equals(DOWN);
    }
}
