package fr.univ_amu.iut.exercice5;

/// Kata 5 - Yahtzee scoring.
///
/// Chaque méthode statique calcule le score selon sa règle pour un lancer de 5
/// dés. Le kata est un classique du refactoring (Yatzy Refactoring Kata) : les
/// solutions "brute-force" fonctionnent vite, mais vous verrez que certaines
/// règles partagent une structure (compter les occurrences de chaque face) qu'on
/// peut factoriser.
///
/// Conseil : implémentez chaque méthode en TDD puis, quand plusieurs sont
/// vertes, cherchez une factorisation. C'est un bon entraînement au
/// TP4 (Refactoring).
public class Yahtzee {

  private Yahtzee() {}

  /// Somme des 5 dés (quelle que soit leur valeur).
  public static int chance(int d1, int d2, int d3, int d4, int d5) {
    int somme = 0;
    somme = d1 + d2 + d3 + d4 + d5;
    return somme;
  }

  /// 50 points si les 5 dés sont identiques, 0 sinon.
  public static int yahtzee(int d1, int d2, int d3, int d4, int d5) {
    int score = 0;
    if (d1 == d2 && d2 == d3 && d3 == d4 && d4 == d5) // if des[i]==des[i+1]??
    score = 50;
    else score = 0;
    return score;
  }

  /// Somme des dés qui montrent la face demandée (utilisé pour ones, twos,
  /// ..., sixes).
  public static int nombres(int face, int[] des) {
    int total = 0;
    for (int i = 0; i < des.length; i++) {
      if (des[i] == face) {
        total += des[i];
      }
    }
    return total;
  }

  /// Valeur de la paire la plus haute (2 * valeur). 0 si aucune paire.
  public static int paire(int d1, int d2, int d3, int d4, int d5) {
    int score = 0;

    int[] faces = compterOccurrences(d1, d2, d3, d4, d5);

    for (int i = 5;
        i >= 0;
        i--) { // parcours en ordre, donc le premier paire trouve toujours le plus haut
      if (faces[i] >= 2) {
        score = 2 * (i + 1);
        return score;
      }
    }
    return 0;
  }

  /// Somme de deux paires de valeurs différentes. 0 sinon.
  public static int deuxPaires(int d1, int d2, int d3, int d4, int d5) {
    int score = 0;

    int[] faces = compterOccurrences(d1, d2, d3, d4, d5);
    int paires = 0;

    for (int i = 5; i >= 0; i--) {
      if (faces[i] >= 2) {

        score += 2 * (i + 1);
        paires++;

        if (paires == 2) {
          return score;
        }
      }
    }
    return 0;
  }

  /// Somme de 3 dés identiques, 0 sinon.
  public static int brelan(int d1, int d2, int d3, int d4, int d5) {
    int score = 0;

    int[] faces = compterOccurrences(d1, d2, d3, d4, d5);

    for (int i = 5; i >= 0; i--) {
      if (faces[i] >= 3) {
        score = 3 * (i + 1);
        return score;
      }
    }
    return 0;
  }

  /// 15 si les 5 dés sont exactement 1-2-3-4-5, 0 sinon.
  public static int petiteSuite(int d1, int d2, int d3, int d4, int d5) {
    int score = 0;
    int[] faces = compterOccurrences(d1, d2, d3, d4, d5);

    for (int i = 0; i < 5; i++) {
      if (faces[i] != 1) {
        return 0;
      } else {
        score = 15;
      }
    }
    return score;
  }

  /// 20 si les 5 dés sont exactement 2-3-4-5-6, 0 sinon.
  public static int grandeSuite(int d1, int d2, int d3, int d4, int d5) {

    int[] faces = compterOccurrences(d1, d2, d3, d4, d5);

    for (int i = 5; i >= 1; i--) {
      if (faces[i] != 1) {
        return 0;
      }
    }
    return 20;
  }

  /// Somme des 5 dés si on a un brelan plus une paire d'une autre valeur,
  /// 0 sinon.
  public static int full(int d1, int d2, int d3, int d4, int d5) {
    int score = 0;

    int[] faces = compterOccurrences(d1, d2, d3, d4, d5);
    boolean brelan = false;
    boolean paire = false;

    for (int i = 5; i >= 1; i--) {
      if (faces[i] == 3) {
        brelan = true;
      } else if (faces[i] == 2) {
        paire = true;
      }
      if (paire && brelan) {
        score = d1 + d2 + d3 + d4 + d5;
      }
    }
    return score;
  }

  public static int[] compterOccurrences(int d1, int d2, int d3, int d4, int d5) {

    int[] faces = new int[6];
    int[] des = new int[] {d1, d2, d3, d4, d5};

    for (int d : des) {
      switch (d) {
        case 1:
          faces[0]++;
          break;
        case 2:
          faces[1]++;
          break;
        case 3:
          faces[2]++;
          break;
        case 4:
          faces[3]++;
          break;
        case 5:
          faces[4]++;
          break;
        case 6:
          faces[5]++;
          break;
      }
    }
    return faces;
  }
}
