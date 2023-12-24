type Choice = {
  text: string;
  correct: boolean;
};

type Question = {
  text: string;
  choices: Choice[];
};

type QuizDuration = {
  days: number;
  hours: number;
};

type Quiz = {
  questions: Question[];
  length: number;
  duration: QuizDuration;
};
