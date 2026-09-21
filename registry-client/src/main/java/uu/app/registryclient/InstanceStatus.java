package uu.app.registryclient;

public enum InstanceStatus {
    UP,
    DOWN;

    public boolean isDown() {
        return this.equals(DOWN);
    }
}
