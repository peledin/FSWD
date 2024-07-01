package ch.zhaw.fswd.powerDate.entity;

public enum EGender {
    MALE, FEMALE;

    @Override
    public String toString() {
        return name();
    }
}
