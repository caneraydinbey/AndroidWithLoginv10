package com.example.caneraydin.androidwithlogin.domains;

/**
 * Created by caneraydin on 29.04.2016.
 */
public class TrainingResponse {

    int trainingResponseID;
    int trainingID;
    String studentUserName;
   // int objectID;
    String trainingResponseFinishTime;//unix
    int trainingResponseScore;
//yapildi ttodo yap 29.04.2016  gonderilip gonderilmedigini kontrol et bu tablodakilerin, ayri bir kolon

    int trainingStarted;//bu sadece benim localde olacak, oraya gitmeyecek.
    int trainingCompleted;//bu sadece benim localde olacak, oraya gitmeyecek.hepsi tamamlaninca bunlarin hepsi 1 olacak
    int responseSent;//bu sadece benim localde olacak, oraya gitmeyecek
    int questionObjectID;
    int answerObjectID;
    int answerTwoObjectID;

    public TrainingResponse( int trainingID, String studentUserName, int questionObjectID, int answerObjectID, int answerTwoObjectID,
                             int trainingResponseScore, String trainingResponseFinishTime,
                            int responseSent, int trainingStarted,int trainingCompleted) {
        this.trainingCompleted = trainingCompleted;
        this.trainingStarted = trainingStarted;
      //  this.trainingResponseID = trainingResponseID;
        this.trainingID = trainingID;
        this.studentUserName = studentUserName;
  //      this.objectID = objectID;
        this.trainingResponseFinishTime = trainingResponseFinishTime;
        this.trainingResponseScore = trainingResponseScore;
        this.responseSent = responseSent;
        this.questionObjectID = questionObjectID;
        this.answerObjectID = answerObjectID;
        this.answerTwoObjectID = answerTwoObjectID;
    }

    /*  public TrainingResponse(int trainingResponseID, int trainingID, String studentUserName, int objectID, int trainingResponseScore, String trainingResponseFinishTime) {
        this.trainingResponseID = trainingResponseID;
        this.trainingID = trainingID;
        this.studentUserName = studentUserName;
        this.objectID = objectID;
        this.trainingResponseFinishTime = trainingResponseFinishTime;
        this.trainingResponseScore = trainingResponseScore;
    }*/

    public TrainingResponse() {
    }

    public int getQuestionObjectID() {
        return questionObjectID;
    }

    public void setQuestionObjectID(int questionObjectID) {
        this.questionObjectID = questionObjectID;
    }

    public int getAnswerObjectID() {
        return answerObjectID;
    }

    public void setAnswerObjectID(int answerObjectID) {
        this.answerObjectID = answerObjectID;
    }

    public int getResponseSent() {
        return responseSent;
    }

    public void setResponseSent(int responseSent) {
        this.responseSent = responseSent;
    }

    public int getTrainingResponseID() {
        return trainingResponseID;
    }

    public void setTrainingResponseID(int trainingResponseID) {
        this.trainingResponseID = trainingResponseID;
    }

    public int getTrainingID() {
        return trainingID;
    }

    public void setTrainingID(int trainingID) {
        this.trainingID = trainingID;
    }

    public String getStudentUserName() {
        return studentUserName;
    }

    public void setStudentUserName(String studentUserName) {
        this.studentUserName = studentUserName;
    }

  //  public int getObjectID() {
   //     return objectID;
   // }

  //  public void setObjectID(int objectID) {
       // this.objectID = objectID;
    //}

    public String getTrainingResponseFinishTime() {
        return trainingResponseFinishTime;
    }

    public void setTrainingResponseFinishTime(String trainingResponseFinishTime) {
        this.trainingResponseFinishTime = trainingResponseFinishTime;
    }

    public int getTrainingResponseScore() {
        return trainingResponseScore;
    }

    public void setTrainingResponseScore(int trainingResponseScore) {
        this.trainingResponseScore = trainingResponseScore;
    }

    public int getTrainingStarted() {
        return trainingStarted;
    }

    public void setTrainingStarted(int trainingStarted) {
        this.trainingStarted = trainingStarted;
    }

    public int getTrainingCompleted() {
        return trainingCompleted;
    }

    public void setTrainingCompleted(int trainingCompleted) {
        this.trainingCompleted = trainingCompleted;
    }

    public int getAnswerTwoObjectID() {
        return answerTwoObjectID;
    }

    public void setAnswerTwoObjectID(int answerTwoObjectID) {
        this.answerTwoObjectID = answerTwoObjectID;
    }

    @Override
    public String toString() {
        return "TrainingResponse{" +
                "trainingResponseID=" + trainingResponseID +
                ", trainingID=" + trainingID +
                ", studentUserName='" + studentUserName + '\'' +
                ", trainingResponseFinishTime='" + trainingResponseFinishTime + '\'' +
                ", trainingResponseScore=" + trainingResponseScore +
                ", trainingStarted=" + trainingStarted +
                ", trainingCompleted=" + trainingCompleted +
                ", responseSent=" + responseSent +
                ", questionObjectID=" + questionObjectID +
                ", answerObjectID=" + answerObjectID +
                ", answerTwoObjectID=" + answerTwoObjectID +
                '}';
    }
}


