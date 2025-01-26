from flask_sqlalchemy import SQLAlchemy # type: ignore

db = SQLAlchemy()

class Request(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    company_owner_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    product_details = db.Column(db.String(255), nullable=False)
    pickup_location = db.Column(db.String(255), nullable=False)
    dropoff_location = db.Column(db.String(255), nullable=False)
    preferred_dates = db.Column(db.String(255), nullable=False)
    status = db.Column(db.String(50), default='open')  # 'open', 'accepted', 'completed'

class Offer(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    truck_owner_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    request_id = db.Column(db.Integer, db.ForeignKey('request.id'), nullable=False)
    offer_details = db.Column(db.String(255), nullable=False)
    status = db.Column(db.String(50), default='pending')  # 'pending', 'accepted', 'rejected' 