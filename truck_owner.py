from .database import db
from datetime import datetime

class TruckOwnerProfile(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    user_id = db.Column(db.Integer, db.ForeignKey('user.id'), nullable=False)
    vehicle_type = db.Column(db.String(120), nullable=False)
    registration_number = db.Column(db.String(120), nullable=False)
    capacity = db.Column(db.String(120))
    availability = db.Column(db.String(120))
    pricing = db.Column(db.String(120))
    created_at = db.Column(db.DateTime, default=datetime.utcnow)
    updated_at = db.Column(db.DateTime, default=datetime.utcnow, onupdate=datetime.utcnow)

    def __repr__(self):
        return '<TruckOwnerProfile %r>' % self.registration_number 