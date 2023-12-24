import { Form, Input, Button, Select, Col, notification } from "antd";
import { ValidateStatus } from "antd/es/form/FormItem";
import { FormEvent, useState } from "react";
const Option = Select.Option;
const FormItem = Form.Item;
const { TextArea } = Input;
import "./NewQuiz.css";
import { PlusCircleOutlined } from "@ant-design/icons";
import { MAX_QUESTIONS } from "../../constants";

type FormQuestion = {
  text: string;
  validateStatus: ValidateStatus;
  errorMsg: string;
};
export default function NewQuiz() {
  const handleSubmit = (event: FormEvent) => {
    event.preventDefault();
  };
  const addChoice = () => {};
  const addQuestion = () => {
    setQuizLength(quizlength + 1);
  };
  const handleQuestionChange = () => {};
  const handleQuizDaysChange = () => {};
  const handleQuizHoursChange = () => {};
  const [quizlength, setQuizLength] = useState(1);
  const [quizQuestions, setQuizQuestions] = useState<FormQuestion[]>([{
    text: "",
    validateStatus: "success",
    errorMsg: "",
  }]);
  const [quizDuration, setQuizDuration] = useState<QuizDuration>({
    days: 1,
    hours: 0,
  });

  const questionsElements = [];

  for (let index = 0; index < quizlength; index += 1) {
    questionsElements.push(
      <div>
        <FormItem
          validateStatus={quizQuestions[index].validateStatus}
          help={quizQuestions[index].errorMsg}
          className="quiz-form-row"
        >
          <TextArea
            placeholder="Enter your question"
            style={{ fontSize: "16px" }}
            autoSize={{ minRows: 3, maxRows: 6 }}
            name="question"
            value={""}
            onChange={handleQuestionChange}
          />
        </FormItem>
        {/*choiceViews*/}
        <FormItem className="quiz-form-row">
          <Button
            type="dashed"
            onClick={addChoice}
            disabled={false /*this.state.choices.length === MAX_CHOICES*/}
          >
            <PlusCircleOutlined /> Add a choice
          </Button>
        </FormItem>
      </div>
    );
  }

  return (
    <div className="new-quiz-container">
      <h1 className="page-title">Create Quiz</h1>
      <div className="new-quiz-content">
        <Form onSubmitCapture={handleSubmit} className="create-quiz-form">
          {questionsElements}
          <FormItem className="quiz-form-row">
            <Col xs={32} sm={16}>
              Quiz Duration:
            </Col>
            <Col xs={24} sm={20}>
              <span style={{ marginRight: "18px" }}>
                <Select
                  placeholder="days"
                  defaultValue={1}
                  onChange={handleQuizDaysChange}
                  value={quizDuration.days}
                  style={{ width: 60 }}
                >
                  {Array.from(Array(8).keys()).map((i) => (
                    <Option key={i}>{i}</Option>
                  ))}
                </Select>{" "}
                &nbsp;Days
              </span>
              <span>
                <Select
                  placeholder="hours"
                  defaultValue={0}
                  onChange={handleQuizHoursChange}
                  value={quizDuration.hours}
                  style={{ width: 60 }}
                >
                  {Array.from(Array(24).keys()).map((i) => (
                    <Option key={i}>{i}</Option>
                  ))}
                </Select>{" "}
                &nbsp;Hours
              </span>
            </Col>
          </FormItem>
          <FormItem className="quiz-form-row">
            <Button
              type="dashed"
              size="large"
              onClick={addQuestion}
              disabled={quizlength === MAX_QUESTIONS}
              className="create-quiz-form-button"
            >
              <PlusCircleOutlined /> Add a question
            </Button>
          </FormItem>
          <FormItem className="quiz-form-row">
            <Button
              type="primary"
              htmlType="submit"
              size="large"
              disabled={false /*this.isFormInvalid()*/}
              className="create-quiz-form-button"
            >
              Create Quiz
            </Button>
          </FormItem>
        </Form>
      </div>
    </div>
  );
}
