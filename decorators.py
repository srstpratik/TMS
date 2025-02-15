from functools import wraps
from flask import request, jsonify
from app.models.user import User

def role_required(required_role):
    def decorator(f):
        @wraps(f)
        def decorated_function(*args, **kwargs):
            user_id = request.headers.get('User-ID')
            user = User.query.get(user_id)
            if user and user.role == required_role:
                return f(*args, **kwargs)
            else:
                return jsonify({"error": "Unauthorized access"}), 403
        return decorated_function
    return decorator 