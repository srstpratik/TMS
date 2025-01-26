from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class RatingReview(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    transaction_id = db.Column(db.Integer, nullable=False)
    reviewer_id = db.Column(db.Integer, nullable=False)
    reviewee_id = db.Column(db.Integer, nullable=False)
    rating = db.Column(db.Integer, nullable=False)
    review = db.Column(db.String(500), nullable=True)
    timestamp = db.Column(db.DateTime, default=db.func.current_timestamp())

    def __init__(self, transaction_id, reviewer_id, reviewee_id, rating, review=None):
        self.transaction_id = transaction_id
        self.reviewer_id = reviewer_id
        self.reviewee_id = reviewee_id
        self.rating = rating
        self.review = review 