/**
 * Jan Gilla
 * 17.09.2016
 * V1.0
 * 
 * Berechnug und Ausgabe einer linearen, Geometrisch-degressiven und Methodenwechsel Abschreibung
 */

package mypack;

import java.text.*;

public class Abschreibung {

	public static void main(String[] args) {
		final double ANSCHKOSTEN = 21000;
		/** Bitte als Ganzzahl nicht als Kommawert, 20% -> 20 */
		final double PROZENTSATZ = 20;
		final int NUTZDAUER = 7;

		double restBuchWert = ANSCHKOSTEN; // Aktueller Restbuchwert
		double abschreibung = 0; // Aktuelle Abschreibung
		double wechselJahr = 0; // Jahr ab dem gewechselt wird

		DecimalFormat ausgFormat = new DecimalFormat("#,##0.00");

		/**
		 * Lineare Abschreibung
		 */
		System.out.println("Lineare Abschreibung:");
		System.out.println("Jahr\t Abschreibung\t Restbuchwert");
		for (int j = 0; j < NUTZDAUER + 1; j++) {
			abschreibung = ANSCHKOSTEN / NUTZDAUER;
			restBuchWert = ANSCHKOSTEN - (j * ANSCHKOSTEN / NUTZDAUER);
			if (j == 0) {
				/** Ausnahmewert fuer NULL */
				abschreibung = 0;
			}
			System.out.println(" " + j + "\t " + String.format("%1$9s", ausgFormat.format(abschreibung)) + " EUR\t "
					+ String.format("%1$9s", ausgFormat.format(restBuchWert)) + " EUR ");
		}

		/**
		 * Geometrisch-degressive Abschreibung
		 */
		restBuchWert = ANSCHKOSTEN;
		abschreibung = 0;
		System.out.println("Geometrisch-degressive Abschreibung:");
		System.out.println("Jahr\t Abschreibung\t Restbuchwert");
		for (int j = 0; j < NUTZDAUER + 1; j++) {
			if (j == 0) {
				/** Ausnahmewert fuer NULL */
				abschreibung = 0;
			} else {
				/** Sonst berechnung der Abschreibung */
				abschreibung = restBuchWert * (PROZENTSATZ / 100);
				restBuchWert -= abschreibung;
			}
			System.out.println(" " + j + "\t " + String.format("%1$9s", ausgFormat.format(abschreibung)) + " EUR\t "
					+ String.format("%1$9s", ausgFormat.format(restBuchWert)) + " EUR ");
		}

		/**
		 * Methodenwechsel Abschreibung
		 */
		restBuchWert = ANSCHKOSTEN;
		abschreibung = 0;
		wechselJahr = NUTZDAUER - (100 / PROZENTSATZ) + 1;
		System.out.println(wechselJahr);
		System.out.println("Methodenwechsel Abschreibung:");
		System.out.println("Jahr\t Abschreibung\t Restbuchwert");
		for (int j = 0; j < NUTZDAUER + 1; j++) {
			if (j == 0) {
				/** Ausnahmewert fuer NULL */
				abschreibung = 0;
			}
			if (j > 0 && j <= wechselJahr) {
				/** Geometrisch-degressive Abschreibung */
				abschreibung = restBuchWert * PROZENTSATZ / 100;
				restBuchWert -= abschreibung;
			} else if (j == wechselJahr + 1) {
				/** Berechnung der Abschreibung und Abzug im Wechseljahr */
				abschreibung = restBuchWert / (NUTZDAUER - j + 1);
				restBuchWert -= abschreibung;
			} else if (j > wechselJahr) {
				/** Lineare Abschreibung */
				restBuchWert -= abschreibung;
			}

			/** Ausgabebereich */
			if (j <= wechselJahr) {
				System.out.println(" " + j + "\t " + String.format("%1$9s", ausgFormat.format(abschreibung)) + " EUR\t "
						+ String.format("%1$9s", ausgFormat.format(restBuchWert)) + " EUR ");
			} else {
				System.out.println(" " + j + "\t " + String.format("%1$9s", ausgFormat.format(abschreibung)) + " EUR\t "
						+ String.format("%1$9s", ausgFormat.format(restBuchWert)) + " EUR Wechsel linear");
			}
		}
	}

}
