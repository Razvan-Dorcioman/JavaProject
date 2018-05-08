import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/*Să se realizeze o aplicație Java care să asigure gestiunea unor adrese de mail dintr-
o firmă. Firma este structurată pe departamente, iar în fiecare departament adresele
vor conţine următoarele câmpuri: nume, prenume, adr_mail, parola, domeniu,
funcţie, tip_cont, activa, ultima_accesare. Aplicația trebuie să permită efectuarea
următoarele operații:
• Încărcare din fişier a adreselor de mail ale firmei, din toate departamentele.
• Adăugare adresă mail pentru un nou angajat.                                               check -> verificare ca nu exista aceeasi adresa de email in baza de date
• Inactivare adrese nefolosite de mai mult de 3 luni.                                       check
• Ştergere adrese mail inactive.                                                            check
• Sortare adrese mail după nume şi prenume angajat de la un anumit departament.             check
• Modificare parolă la o anumită adresă de mail.                                            check ->  scriere+verificarea existentei in bd
• Adăugare departament nou.
• Salvare adrese de mail în fişier, pe departamente.*/
public class Main {
    public static void main(String[] args) throws SQLException {

        Menu f = new Menu("Java Project");
        f.setVisible(true);
    }

}
