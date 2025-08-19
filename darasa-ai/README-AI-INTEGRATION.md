# Darasa AI - Question Generation Feature

## 🤖 AI-Powered Question Generation

Darasa AI includes intelligent question generation using OpenAI's GPT models!

## 🚀 Setup

### 1. Environment Variables
Add your OpenAI API key to your environment:

```bash
export OPENAI_API_KEY="sk-your-openai-api-key-here"
```

Or add it to your `.env` file:
```
OPENAI_API_KEY=sk-your-openai-api-key-here
```

### 2. Get OpenAI API Key
1. Visit [OpenAI Platform](https://platform.openai.com/)
2. Sign up/login to your account
3. Go to API Keys section
4. Create a new API key
5. Copy the key (starts with `sk-`)

## 📚 API Endpoint

### Generate Questions for a Course

**Endpoint:** `POST /api/courses/{courseId}/generate-questions`

**Authentication:** Required (JWT Bearer token)

**Request Body:**
```json
{
  "topic": "Introduction to Variables",
  "numberOfQuestions": 5,
  "difficulty": "medium",
  "questionType": "multiple_choice",
  "additionalContext": "Focus on basic variable declaration and initialization"
}
```

**Response:**
```json
{
  "message": "Questions generated successfully using AI",
  "courseId": 1,
  "courseName": "Introduction to Programming",
  "topic": "Introduction to Variables",
  "totalQuestions": 5,
  "questions": [
    {
      "questionText": "Which of the following is the correct way to declare an integer variable in Java?",
      "questionType": "multiple_choice",
      "difficulty": "medium",
      "options": [
        "int myVariable;",
        "integer myVariable;",
        "var myVariable;",
        "number myVariable;"
      ],
      "correctAnswer": "int myVariable;",
      "explanation": "In Java, 'int' is the correct keyword to declare integer variables.",
      "topic": "Introduction to Variables"
    }
  ],
  "generatedAt": "2024-01-15T10:30:00"
}
```

## 🎯 Request Parameters

| Parameter | Type | Required | Default | Description |
|-----------|------|----------|---------|-------------|
| `topic` | String | Yes | - | The topic/subject for question generation |
| `numberOfQuestions` | Integer | No | 5 | Number of questions (1-20) |
| `difficulty` | String | No | "medium" | Difficulty level: "easy", "medium", "hard" |
| `questionType` | String | No | "multiple_choice" | Type: "multiple_choice", "short_answer", "essay" |
| `additionalContext` | String | No | - | Extra context to guide question generation |

## 🧪 Testing Examples

### 1. Basic Multiple Choice Questions
```bash
curl -X POST http://localhost:8080/api/courses/1/generate-questions \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "topic": "Java Loops",
    "numberOfQuestions": 3,
    "difficulty": "easy",
    "questionType": "multiple_choice"
  }'
```

### 2. Essay Questions with Context
```bash
curl -X POST http://localhost:8080/api/courses/1/generate-questions \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "topic": "Object-Oriented Programming",
    "numberOfQuestions": 2,
    "difficulty": "hard",
    "questionType": "essay",
    "additionalContext": "Focus on inheritance and polymorphism concepts"
  }'
```

### 3. Short Answer Questions
```bash
curl -X POST http://localhost:8080/api/courses/1/generate-questions \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "topic": "Database Normalization",
    "numberOfQuestions": 4,
    "difficulty": "medium",
    "questionType": "short_answer"
  }'
```

## 🔧 Features

### Smart Context Awareness
- Uses course name and description for better question generation
- Incorporates additional context you provide
- Maintains consistency with course difficulty level

### Multiple Question Types
- **Multiple Choice**: 4 options with correct answer
- **Short Answer**: Open-ended questions requiring brief responses
- **Essay**: Comprehensive questions for detailed answers

### Quality Assurance
- Includes explanations for correct answers
- Maintains topic consistency
- Provides fallback questions if AI parsing fails
