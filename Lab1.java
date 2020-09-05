/* Samiksha Modi - 2019331
AP Lab1 */

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
    String institute; //null if not assigned
    int recoveryDays;

    Patient(int i, String n, float t, int o, int a) {
        id = i;
        name = n;
        temp = t;
        olevel = o;
        age = a;
        admitted = false;
        institute=null;
    }

    //Query 1
    void getAdmission(String h,int r)
    {
        admitted = true;
        institute=h;
        recoveryDays=r;
    }

    //Query 7
    void displayParticularPatient() {
        System.out.println(name);
        System.out.println("Temperature is " + temp);
        System.out.println("Oxygen level is " + olevel);

        System.out.print("Admission Status - ");
        if (admitted) {
            System.out.println("Admitted");
            System.out.println("Admitting Institute - " + institute);
        } else
            System.out.println("Not Admitted");
    }

    //Query 8
    void displayAllPatients() {
        System.out.println(id + " " + name);
    }

    //Query 9
    void displayPatientInInstitute() {
        System.out.println(name + ", recovery time is " + recoveryDays + " days");
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
        patientList = new ArrayList<>();
        healthcareList = new ArrayList<>();
    }

    void addPatients() {

        System.out.print("Input number of patients in camp: ");
        int n = in.nextInt();
        System.out.println("Input details of patients present is camp");
        System.out.println("(Name    Temperature    Oxygen Levels    Age)");
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

        //if beds available then check if a patient is admitted. If not admitted then check eligibility criteria
        //first fill all beds with oxy level criteria. If still beds left then fill with body temp criteria

        //According to oxy level criteria
        for (Patient p : patientList) {
            //if no more beds available then break loop
            if (h.availableBeds <= 0)
                break;

            //All the attributes of patient and healthcare are checked here since i don't want
            //either of them to be accessible by the other.
            if (!p.admitted) {
                if (p.olevel >= h.minOxy) {
                    System.out.print("Recovery days for admitted patient ID " + p.id + "- ");
                    int r = in.nextInt();
                    p.getAdmission(h.name,r);
                    h.availableBeds-=1;
                }
            }
        }

        //According to temp level criteria
        for (Patient p : patientList) {
            //if no more beds available then break loop
            if (h.availableBeds <= 0)
                break;

            if (!p.admitted) {
                if (p.temp <= h.maxTemp) {
                    System.out.print("Recovery days for admitted patient ID " + p.id + "- ");
                    int r = in.nextInt();
                    p.getAdmission(h.name,r);
                    h.availableBeds-=1;
                }
            }
        }

        //if no more beds available, set status to closed
        if (h.availableBeds <= 0)
            h.admissionOpen = false;
    }

    //Query 2
    void removeAdmittedAccount() {
        ArrayList<Patient> toRemove = new ArrayList<>();
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
        ArrayList<Healthcare> toRemove = new ArrayList<>();
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
                p.displayParticularPatient();
            }
        }
    }

    //Query 8
    void displayAllPatients() {
        for (Patient p : patientList)
            p.displayAllPatients();
    }

    //Query 9
    void displayPatientInInstitute(String s) {
        for (Patient p : patientList) {
            if (p.institute != null && p.institute.equals(s)) {
                p.displayPatientInInstitute();
            }
        }
    }
}

public class Lab1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Camp camp = new Camp();
        camp.addPatients();

        while (camp.unassignedCount() > 0) {
            System.out.print("Input Query: ");
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
        System.out.println("All patients admitted.");
    }
}
