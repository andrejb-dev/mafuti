package sk.badand.mafuti.ui.menu;

import com.airhacks.afterburner.injection.Injector;
import javafx.scene.control.ButtonType;
import sk.badand.mafuti.model.Club;
import sk.badand.mafuti.model.match.PlayableMatch;
import sk.badand.mafuti.services.CalendarService;
import sk.badand.mafuti.services.UserService;
import sk.badand.mafuti.services.inject.UsersClubHolder;
import sk.badand.mafuti.ui.club.staff.StaffView;
import sk.badand.mafuti.ui.club.team.TeamPresenter;
import sk.badand.mafuti.ui.club.team.TeamView;
import sk.badand.mafuti.ui.facilities.academy.AcademyView;
import sk.badand.mafuti.ui.facilities.fitness.FitnessView;
import sk.badand.mafuti.ui.facilities.office.OfficeView;
import sk.badand.mafuti.ui.facilities.stadium.StadiumView;
import sk.badand.mafuti.ui.finances.expenses.ExpensesView;
import sk.badand.mafuti.ui.finances.incomes.IncomesView;
import sk.badand.mafuti.ui.finances.loans.LoansView;
import sk.badand.mafuti.ui.finances.salaries.SalariesView;
import sk.badand.mafuti.ui.finances.summary.SummaryView;
import sk.badand.mafuti.ui.manager.ManagerView;
import sk.badand.mafuti.ui.navigation.AbstractNavigator;
import sk.badand.mafuti.ui.prematch.PrematchView;
import sk.badand.mafuti.ui.timeprogress.TimeprogressView;
import sk.badand.mafuti.ui.training.players.PlayersView;
import sk.badand.mafuti.ui.training.programs.ProgramsView;
import sk.badand.mafuti.ui.training.rehab.RehabView;
import sk.badand.mafuti.ui.world.competitions.CompetitionsView;
import sk.badand.mafuti.ui.world.jobs.JobsView;
import sk.badand.mafuti.util.QuestionDialog;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MenuPresenter extends AbstractNavigator {
    private static final Logger LOG = Logger.getLogger(MenuPresenter.class.getName());
    @Inject
    CalendarService calendarService;
    @Inject
    UserService userService;
    Club club;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        club = userService.getClub();

        Injector.setModelOrService(UsersClubHolder.class, new UsersClubHolder(club));//FIXME temporary,
    }


    public void processTime() {
        LOG.log(Level.FINE, "processTime");
        if (calendarService.isTeamPlayingToday(club.getTeams().get(0))) {
            new QuestionDialog("yes or no").showAndWait()
                    .filter(result -> result == ButtonType.OK)
                    .ifPresent(result -> {
                        PlayableMatch managerMatch = calendarService.matchToday(club.getTeams().get(0)).get();
                        Injector.setModelOrService(PlayableMatch.class, managerMatch);
                        final PrematchView prematchView = new PrematchView();
                        navigator.load(prematchView);
                    });
        } else {
            navigator.load(new TimeprogressView());
        }
    }

    public void showATeam() {
        LOG.log(Level.FINE, "showATeam");
        final TeamView teamView = new TeamView();
        ((TeamPresenter) teamView.getPresenter()).setTitle("a-team");
        navigator.load(teamView);
    }

    public void showBTeam() {
        LOG.log(Level.FINE, "showBTeam");
        final TeamView teamView = new TeamView();
        ((TeamPresenter) teamView.getPresenter()).setTitle("b-team");
        navigator.load(teamView);
    }

    public void showYouthTeam() {
        LOG.log(Level.FINE, "showYouthTeam");
        final TeamView teamView = new TeamView();
        ((TeamPresenter) teamView.getPresenter()).setTitle("youth-team");
        navigator.load(teamView);
    }

    public void showStaff() {
        LOG.log(Level.FINE, "showStaff");
        navigator.load(new StaffView());
    }

    public void showPlayers() {
        LOG.log(Level.FINE, "showPlayers");
        navigator.load(new PlayersView());
    }

    public void showOnRehab() {
        LOG.log(Level.FINE, "showOnRehab");
        navigator.load(new RehabView());
    }

    public void showPrograms() {
        LOG.log(Level.FINE, "showPrograms");
        navigator.load(new ProgramsView());
    }

    public void showStadium() {
        LOG.log(Level.FINE, "showStadium");
        navigator.load(new StadiumView());
    }

    public void showOffice() {
        LOG.log(Level.FINE, "showOffice");
        navigator.load(new OfficeView());
    }

    public void showYouthAcademy() {
        LOG.log(Level.FINE, "showYouthAcademy");
        navigator.load(new AcademyView());
    }

    public void showFitnessMedic() {
        LOG.log(Level.FINE, "showFitnessMedic");
        navigator.load(new FitnessView());
    }
    
    public void showSummary() {
        LOG.log(Level.FINE, "showSummary");
        navigator.load(new SummaryView());
    }

    public void showExpenses() {
        LOG.log(Level.FINE, "showExpenses");
        navigator.load(new ExpensesView());
    }

    public void showIncomes() {
        LOG.log(Level.FINE, "showIncomes");
        navigator.load(new IncomesView());
    }

    public void showSalaries() {
        LOG.log(Level.FINE, "showSalaries");
        navigator.load(new SalariesView());
    }

    public void showLoans() {
        LOG.log(Level.FINE, "showLoans");
        navigator.load(new LoansView());
    }

    public void showCompetitions() {
        LOG.log(Level.FINE, "showCompetitions");
        navigator.load(new CompetitionsView());
    }

    public void showStatistics() {
        LOG.log(Level.FINE, "showStatistics");//TODO rethink, maybe include in competitions somehow
    }

    public void showJobs() {
        LOG.log(Level.FINE, "showJobs");
        navigator.load(new JobsView());
    }

    public void showPersonal() {
        LOG.log(Level.FINE, "showPersonal");
        navigator.load(new ManagerView());
    }
}