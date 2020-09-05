/*input
5
Ram 98.4 94 25
Sam 100.4 92 55
Jim 104 91 61
Tim 99 93 60
Kim 100 91 48
 */

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
    ArrayList<Patient> patientList;
    ArrayList<Healthcare> healthcareList;
    Scanner in = new Scanner(System.in);

    Camp() {
        patientList = new ArrayList<Patient>();
        healthcareList = new ArrayList<Healthcare>();
    }

    void addPatients() {

        int n = in.nextInt();
        int id = 1;
        for (int i = 0; i < n; i += 1) {
            String name = in.next();
            float temp = in.nextFloat();
            int olevel = in.nextInt();
            int age = in.nextInt();
            patientList.add(new Patient(id, name, temp, olevel, age));
            id++;
        }
    }


    //Query 1
    void addHealthcareInstitute() {
        System.out.println("Details of Healthcare Institute to be added");
        System.out.print("Name: ");
        String name = in.next();
        System.out.print("Temperature Criteria - ");
        float temp = in.nextFloat();
        System.out.print("Oxygen Levels - ");
        int olevel = in.nextInt();
        System.out.print("Number of Available beds - ");
        int beds = in.nextInt();
        Healthcare h = new Healthcare(name, temp, olevel, beds);
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
                    h.availableBeds -= 1;
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
                    h.availableBeds -= 1;
                    p.setInstitute(h);
                    System.out.print("Recovery days for admitted patient ID " + p.id + "- ");
                    int r = in.nextInt();
                    p.setRecoveryDays(r);
                }
            }
        }

        if (h.availableBeds <= 0)
            h.admissionOpen = false;
    }


    //Query 2
    void removeAdmittedAccount() {
        ArrayList<Patient> toRemove = new ArrayList<Patient>();
        System.out.println("Account ID removed of admitted patients");
        for (Patient p : patientList) {
            if (p.admitted) {
                System.out.println(p.id);
                toRemove.add(p);
            }
        }

        for (Patient x : toRemove) {
            patientList.remove(x);
        }

    }

    //Query 3
    void removeClosedAccount() {
        ArrayList<Healthcare> toRemove = new ArrayList<Healthcare>();
        System.out.println("Accounts removed of Institute whose admission is closed");
        for (Healthcare h : healthcareList) {
            if (!h.admissionOpen) {
                System.out.println(h.name);
                toRemove.add(h);
            }
        }

        for (Healthcare x : toRemove) {
            healthcareList.remove(x);
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

    //Query 5
    int displayOpenAccount() {
        int cnt = 0;
        for (Healthcare h : healthcareList) {
            if (h.admissionOpen)
                cnt++;
        }
        return cnt;
    }

    //Query 6
    void displayParticularHealthcare(String s) {
        for (Healthcare h : healthcareList) {
            if (h.name.equals(s)) {
                h.display();
            }
        }
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

    //Query 9
    void displayPatientInInstitute(String s) {
        for (Patient p : patientList) {
            if (p.Institute != null && p.Institute.name.equals(s)) {
                System.out.println(p.name + ", recovery time is " + p.recoveryDays + " days");
            }
        }
    }

    void display() {
        for (Patient p : patientList)
            System.out.println(p.id + " " + p.name + " " + p.temp + " " + p.olevel + " " + p.age);
    }

}

public class Lab1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Camp camp = new Camp();
        camp.addPatients();

        while (camp.unassignedCount() > 0) {
            int query = in.nextInt();
            switch (query) {
                case 1:
                    camp.addHealthcareInstitute();
                    break;
                case 2:
                    camp.removeAdmittedAccount();
                    break;
                case 3:
                    camp.removeClosedAccount();
                    break;
                case 4:
                    System.out.println(camp.unassignedCount() + " patients");
                    break;
                case 5:
                    System.out.println(camp.displayOpenAccount() + " institutes are admitting patients currently");
                    break;
                case 6:
                    String s = in.next();
                    camp.displayParticularHealthcare(s);
                    break;
                case 7:
                    int x = in.nextInt();
                    camp.displayParticularPatient(x);
                    break;
                case 8:
                    camp.displayAllPatients();
                    break;
                case 9:
                    String st = in.next();
                    camp.displayPatientInInstitute(st);
                    break;
            }
        }
    }
}










