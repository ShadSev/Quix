package se.quiz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016-09-28.
 */

@Controller
public class QuizController {
    public List<String> funshit = Arrays.asList("Sweet", "Sweet", "Sweet");
    @Autowired
    private QuizRepository quizRepository;

//
//    @RequestMapping("/")
//    public ModelAndView toQuiz(){
//        return new ModelAndView("/Quiz")
//                .addObject("Question", "Aight")
//                .addObject("choices", funshit);
//    }

    @RequestMapping(method = RequestMethod.POST, path="/getQuiz")
    public ModelAndView showQuiz(@RequestParam int quizID) {
        ModelAndView modelAndView = new ModelAndView("/quzz").addObject("quizz", quizRepository.getQuiz(quizID)).addObject("questions",quizRepository.getQuestionsForQuiz(quizID));
        List<Question> questions = quizRepository.getQuestionsForQuiz(quizID);
        int i = 1;

        for (Question question:questions) {
            String s = "choices"+i;
            modelAndView.addObject(s,quizRepository.getChoicesForQuestion(question.questionID));
            i++;
        }
        return modelAndView;
    }

    @RequestMapping(path="/changeQuestion")
    public ModelAndView changeQuestion(@RequestParam int questionID) throws SQLException {
        return new ModelAndView("/question").addObject("question", quizRepository.getQuestion(questionID)).addObject("choices",quizRepository.getChoicesForQuestion(questionID));
    }
    @RequestMapping(path="/nextQuestion")
    public ModelAndView nextQuestion(@RequestParam int questionID) throws SQLException {
        int nextID = questionID+1;
        return new ModelAndView("/question").addObject("question", quizRepository.getQuestion(nextID)).addObject("choices",quizRepository.getChoicesForQuestion(nextID));
    }

    @RequestMapping(path="/previousQuestion")
    public ModelAndView previousQuestion(@RequestParam int questionID) throws SQLException {
        int nextID = questionID-1;
        return new ModelAndView("/question").addObject("question", quizRepository.getQuestion(nextID)).addObject("choices",quizRepository.getChoicesForQuestion(nextID));
    }

    @RequestMapping(path="/quizzes")
    public ModelAndView listQuiz() throws SQLException {
        return new ModelAndView("/quizzes")
                .addObject("quizzes", quizRepository.listQuiz());
    }

    @RequestMapping(path="/questions")
    public ModelAndView listQuestions() throws SQLException {
        return new ModelAndView("/questions")
                .addObject("questions", quizRepository.listQuestions());
    }

    //Visa quizfrågor
    @RequestMapping("/Quiz")
    public ModelAndView displayQuiz() {
        return new ModelAndView("/Quiz");
    }



    //posta svaren
    @RequestMapping("/result")
    public ModelAndView postAndContinue(@RequestParam String test) {
        System.out.println(test);
        return new ModelAndView("/Results");
    }

    //Visa resultat
    @RequestMapping("/Results")
    public ModelAndView displayResults(){ return new ModelAndView("/Results");}

}

