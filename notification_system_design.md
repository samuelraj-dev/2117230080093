# Stage 1

## Core Actions

- Fetch notifications
- Mark notification as read
- Create notifications
- Receive realtime notifications

## APIs

### Get Notifications
GET /api/notifications?page=1&limit=10&type=Placement

Response:
```json
{
  "notifications": []
}
```

### Mark Notification As Read
PATCH /api/notifications/{id}/read

### Create Notification
POST /api/notifications

Request:
```json
{
  "type": "Placement",
  "message": "Google hiring"
}
```

## Realtime Notifications

WebSocket can be used for realtime notifications.

Flow:
1. Client connects using WebSocket
2. Backend pushes notifications in realtime
3. Frontend updates UI in realtime

Benefits:
- Better user experience

---

# Stage 2

## Database Choice

PostgreSQL is used because:
- Structured relational data, ACID compliance
- Feature Rich and OpenSource
- Scalable for large data

## Schema

### students
```sql
id BIGINT PRIMARY KEY
name VARCHAR(100)
email VARCHAR(100)
```

### notifications
```sql
id BIGINT PRIMARY KEY
studentId BIGINT
notificationType VARCHAR(20)
message TEXT
isRead BOOLEAN
createdAt TIMESTAMP
```

## Scaling Problems

- Large table growth
- Slow reads
- Increased DB load

## Solutions

- Indexing
- Pagination
- Partitioning
- Caching

---

# Stage 3

## Why Query Is Slow

The query becomes slow because:
- Large notification table
- Full table scan

## Better Index

```sql
CREATE INDEX idx_notifications
ON notifications(studentID, isRead, createdAt DESC);
```

## Why Not Index Every Column

Adding indexes on every column:
- Increases storage
- Slows inserts/updates

## Query

```sql
SELECT *
FROM notifications
WHERE notificationType = 'Placement'
AND createdAt >= NOW() - INTERVAL '7 days';
```

---

# Stage 4

## Performance Improvements

### Redis Caching
Frequently accessed notifications can be cached.

### Pagination
Load notifications page by page.

### WebSocket
Avoid continuous polling.

## Tradeoffs

- Redis increases RAM usage
- WebSocket requires persistent connections
- Pagination increases API complexity

---

# Stage 5

## Problems In Current Implementation

- Sequential processing
- Failure handling is difficult
- Blocking operations
- Not Robust enough

## Better Design

Use:
- Message Queue or Redis
- Async programming
- Retry mechanisms

---

# Stage 6

## Priority Logic

Priority is determined using:
- Placement > Result > Event
- Recent notifications get higher priority than read and older unred 

## Approach

1. Assign weight to notification type
2. Sort by:
    - weight
    - timestamp
3. Return top 10 notifications

## Efficient Maintenance

A priority queue data structure can be used to maintain top notifications efficiently when new notifications arrive.