package model;

public class Employee extends Person {
    private int access_level;

    public int getAccess_level() {
        return access_level;
    }

    public void setAccess_level(int access_level) {
        this.access_level = access_level;
    }

    @Override
    public int compareTo(Person o) {
        return super.nif - o.nif;
    }

    @Override
    public String toString() {

        String print = """
                Employee info:
                - NIF: %d
                - Name: %s
                - Birth Date: %s
                - Address: %s
                - Access Level: %d
                """;
        return String.format(print, nif, name, birht_date, address, access_level);
    }
}
