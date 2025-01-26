from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class AuditLog(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    user_id = db.Column(db.Integer, nullable=False)
    action = db.Column(db.String(255), nullable=False)
    timestamp = db.Column(db.DateTime, default=db.func.current_timestamp())
    role = db.Column(db.String(20), nullable=False)

    def __init__(self, user_id, action, role):
        self.user_id = user_id
        self.action = action
        self.role = role 