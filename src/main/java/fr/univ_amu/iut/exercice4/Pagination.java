package fr.univ_amu.iut.exercice4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

/// Kata 4 - Pagination.
///
/// Kata algorithmique avec beaucoup de cas limites. Idéal pour pratiquer la
/// **discipline TDD** : on active les tests dans l'ordre (du plus simple au plus
/// complexe) et on résiste à la tentation d'anticiper.
public class Pagination {

  private final int courant;
  private final int total;
  private final String espace = " ";
  private final String ellipses = " ... ";

  public Pagination(int courant, int total) {
    this.courant = courant;
    this.total = total;
  }

  StringBuilder sortie = new StringBuilder();

  /// Retourne la représentation textuelle de la barre de pagination.
  ///
  /// Format : pages séparées par des espaces, page courante entre parenthèses,
  /// `...` pour combler les trous quand il y a plus de 7 pages au total.
  public String afficher() {
    // StringBuilder sortie = new StringBuilder();
    // TODO kata 4 : construire la chaîne de pagination selon les règles
    // du README. Activez les tests dans l'ordre, ils vous guident :
    // - d'abord le cas "total <= 7" (affichage complet)
    // - puis le cas "beaucoup de pages" avec gestion des ellipses

    // Si total <= 7 : afficher toutes les pages
    // Sinon : afficher 1, courant-1, courant, courant+1, total, avec ... entre deux
    // numéros si l'écart est > 1

    // cas "beaucoup de pages" --appel afficherBeacoup
    if (total > 7) return afficherEllipses();

    // cas "total <= 7" (affichage complet) --appel afficherComplet
    return afficherComplet();
  }

  private String afficherComplet() {
    StringBuilder sortie = new StringBuilder();
    for (int i = 1; i <= this.total; i++) {
      if (i == this.courant) sortie.append(formatPage(i));
      else sortie.append(i);
      if (i != this.total) sortie.append(espace);
    }
    return sortie.toString();
  }

  private String afficherEllipses() {
    StringBuilder sortie = new StringBuilder();
    List<Integer> pages = pagesAAfficher();

    for (int i = 0; i < pages.size(); i++) {
      sortie.append(formatPage(pages.get(i)));

      if (i < pages.size() - 1) sortie.append(separateurEntre(pages.get(i), pages.get(i + 1)));
    }
    return sortie.toString();
  }

  private List<Integer> pagesAAfficher() // la liste des numéros à montrer)
      {
    List<Integer> pagesList =
        new ArrayList<>(
            Arrays.asList(1, this.courant - 1, this.courant, this.courant + 1, this.total));
    pagesList.removeIf(page -> page < 1);
    pagesList = new ArrayList<>(new LinkedHashSet<>(pagesList));
    Collections.sort(pagesList);
    return pagesList;
  }

  private String formatPage(int page) { // (un seul numéro avec ou sans parenthèses)
    if (this.courant == page) return "(" + page + ")";
    else return String.valueOf(page);
  }

  private String separateurEntre(
      int courant, int suivant) // (espace ou ellipses "..." selon l'écart)
      {
    if (suivant - courant > 1) return ellipses;
    else return espace;
  }
}
