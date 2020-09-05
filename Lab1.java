import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    int id;
    String name;
    float temp;
    int olevel;
    int age;
    boolean admitted; //false if not admitted. true if admitted
    Healthcare Institute;
    int recoveryDays;

    Patient(int i, String n, float t, int o, int a) {
        id = i;
        name = n;
        temp = t;
        olevel = o;
        age = a;
        admitted = false;
    }

    void setInstitute(Healthcare h) {
        Institute = h;
    }

    void setRecoveryDays(int r) {
        recoveryDays = r;
    }
}

class Healthcare {
    String name;
    float maxTemp;
    int minOxy;
    int availableBeds;
    boolean admissionOpen; //false if closed. true if open

    Healthcare(String n, float t, int o, int a) {
        name = n;
        maxTemp = t;
        minOxy = o;
        availableBeds = a;
        admissionOpen = true;
        display();
    }

    //Query 6
    void display() {
        System.out.println(name);
        System.out.println("Temperature should be <= " + maxTemp);
        System.out.println("Oxygen levels should be >= " + minOxy);
        System.out.println("Number of Available beds - " + availableBeds);
        System.out.print("Admission Status - ");
        if (!admissionOpen)
            System.out.println("CLOSED");
        else
            System.out.println("OPEN");
    }

}

class Camp {
    ArrayList<Patient> patientList = new ArrayList<Patient>();
    ArrayList<Healthcare> healthcareList = new ArrayList<Healthcare>();
    Scanner in = new Scanner(System.in);

    Camp(ArrayList<Patient> l) {
        patientList = l;
    }

    //Query 1
    void addHealthcareInstitute(Healthcare h) {
        healthcareList.add(h);
        admitPatients(h);
    }

    void admitPatients(Healthcare h) {

        //if beds available then check if a patient is admitted
        //if not admitted then check eligibility criteria
        //first fill all beds with oxy level criteria
        //if still beds left then fill with body temp criteria

        //According to oxy level criteria
        for (Patient p : patientList) {
            //if no more beds available then break loop
            if (h.availableBeds <= 0)
                break;

            if (!p.admitted) {
                if (p.olevel >= h.minOxy) {
                    p.admitted = true;
                    h.availableBeds-=1;
                    p.setInstitute(h);
                    System.out.print("Recovery days for admitted patient ID " + p.id + "- ");
                    int r = in.nextInt();
                    p.setRecoveryDays(r);
                }
            }
        }

        //According to temp level criteria
        for (Patient p : patientList) {
            //if no more beds available then break loop
            if (h.availableBeds <= 0)
                break;

            if (!p.admitted) {
                if (p.olevel <= h.maxTemp) {
                    p.admitted = true;
                    h.availableBeds-=1;
                    p.setInstitute(h);
                    System.out.print("Recovery days for admitted patient ID " + p.id + "- ");
                    int r = in.nextInt();
                    p.setRecoveryDays(r);
                }
            }
        }
    }


    //Query 2
    void removeAdmittedAccount() {
        ArrayList<Patient> toRemove=new ArrayList<Patient>();
        for (Patient p : patientList) {
            if (p.admitted) {
                System.out.println(p.id);
                toRemove.add(p);
            }
        }

        for (Patient x: toRemove)
        {
            patientList.remove(x);
        }

    }

    //Query 4
    int unassignedCount() {
        int cnt = 0;
        for (Patient p : patientList) {
            if (!p.admitted)
                cnt++;
        }
        return cnt;
    }

    //Query 7
    void displayParticularPatient(int id) {
        for (Patient p : patientList) {
            if (p.id == id) {
                System.out.println(p.name);
                System.out.println("Temperature is " + p.temp);
                System.out.println("Oxygen level is " + p.olevel);

                System.out.print("Admission Status - ");
                if (p.admitted) {
                    System.out.println("Admitted");
                    System.out.println("Admitting Institute - " + p.Institute.name);
                } else
                    System.out.println("Not Admitted");
            }
        }
    }

    //Query 8
    void displayAllPatients() {
        for (Patient p : patientList)
            System.out.println(p.id + " " + p.name);
    }

    void display() {
        for (Patient p : patientList)
            System.out.println(p.id + " " + p.name + " " + p.temp + " " + p.olevel + " " + p.age);
    }

}

public class Lab1 {

    public static void main(String[] args) {
        ArrayList<Patient> list = new ArrayList<Patient>();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int id = 1;
        for (int i = 0; i < n; i += 1) {
            String name = in.next();
            float temp = in.nextFloat();
            int olevel = in.nextInt();
            int age = in.nextInt();
            list.add(new Patient(id, name, temp, olevel, age));
            id++;
        }

        Camp camp = new Camp(list);

        while (camp.unassignedCount() > 0) {
            int query = in.nextInt();
            switch (query) {
                case 1:
                    System.out.println("Details of Healthcare Institute to be added");
                    System.out.print("Name: ");
                    String name = in.next();
                    System.out.print("Temperature Criteria - ");
                    float temp = in.nextFloat();
                    System.out.print("Oxygen Levels - ");
                    int olevel = in.nextInt();
                    System.out.print("Number of Available beds - ");
                    int beds = in.nextInt();
                    camp.addHealthcareInstitute(new Healthcare(name, temp, olevel, beds));
                    break;
                case 2:
                    camp.removeAdmittedAccount();
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println(camp.unassignedCount()+" patients");
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    //System.out.print("Input Patient id whose details to display: ");
                    int x = in.nextInt();
                    camp.displayParticularPatient(x);
                    break;
                case 8:
                    camp.displayAllPatients();
                    break;
                case 9:
                    break;

            }

        }
    }
}










